package io.github.JayeshD911.Splitwise.strategies;

import io.github.JayeshD911.Splitwise.models.Expense;
import io.github.JayeshD911.Splitwise.models.ExpenseUser;
import io.github.JayeshD911.Splitwise.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Objects;


@Component
public class HeapSettleUpStrategy implements SettleUpStrategy {

    @Override
    public List<Expense> settleUp(List<ExpenseUser> expenses) {
        System.out.println("Settling up using HeapSettleUpStrategy");

        // Group ExpenseUser entries by Expense id
        Map<Long, List<ExpenseUser>> byExpense = new HashMap<>();
        for (ExpenseUser eu : expenses) {
            if (eu.getExpense() == null || eu.getExpense().getId() == null) continue;
            byExpense.computeIfAbsent(eu.getExpense().getId(), k -> new ArrayList<>()).add(eu);
        }

        // Calculate net balances per user id (positive => should receive, negative => owes)
        Map<Long, Double> net = new HashMap<>();
        Map<Long, User> usersById = new HashMap<>();
        for (List<ExpenseUser> list : byExpense.values()) {
            if (list.isEmpty()) continue;
            Expense e = list.get(0).getExpense();
            if (e == null) continue;
            double total = e.getAmount() == null ? 0.0 : e.getAmount();
            User payer = e.getCreatedBy();

            for (ExpenseUser eu : list) {
                if (eu.getUser() == null || eu.getUser().getId() == null) continue;
                usersById.putIfAbsent(eu.getUser().getId(), eu.getUser());
            }

            double payerShare = 0.0;
            if (payer != null && payer.getId() != null) {
                for (ExpenseUser eu : list) {
                    if (eu.getUser() != null && eu.getUser().getId() != null && eu.getUser().getId().equals(payer.getId())) {
                        payerShare = eu.getAmount();
                        break;
                    }
                }
            }

            for (ExpenseUser eu : list) {
                User u = eu.getUser();
                if (u == null || u.getId() == null) continue;
                double amt = eu.getAmount();
                if (payer != null && payer.getId() != null && u.getId().equals(payer.getId())) {
                    double change = total - payerShare;
                    net.put(u.getId(), net.getOrDefault(u.getId(), 0.0) + change);
                } else {
                    net.put(u.getId(), net.getOrDefault(u.getId(), 0.0) - amt);
                }
            }
        }

        // Priority queues for creditors and debtors
        class UserBalance {
            Long userId;
            double balance;
            UserBalance(Long id, double b) { userId = id; balance = b; }
        }

        PriorityQueue<UserBalance> creditors = new PriorityQueue<>(Comparator.comparingDouble((UserBalance ub) -> -ub.balance));
        PriorityQueue<UserBalance> debtors = new PriorityQueue<>(Comparator.comparingDouble((UserBalance ub) -> ub.balance));

        for (Map.Entry<Long, Double> entry : net.entrySet()) {
            double bal = entry.getValue();
            if (Math.abs(bal) < 0.0001) continue;
            if (bal > 0) creditors.add(new UserBalance(entry.getKey(), bal));
            else debtors.add(new UserBalance(entry.getKey(), bal));
        }

        List<Expense> settlements = new ArrayList<>();

        while (!creditors.isEmpty() && !debtors.isEmpty()) {
            UserBalance cred = Objects.requireNonNull(creditors.poll());
            UserBalance debt = Objects.requireNonNull(debtors.poll());

            double amount = Math.min(cred.balance, -debt.balance);

            // Create a lightweight Expense to represent the settlement
            Expense settlement = new Expense();
            User creditor = Objects.requireNonNull(usersById.get(cred.userId));
            settlement.setDescription("Settlement: " + debt.userId + " -> " + cred.userId);
            settlement.setAmount(amount);
            settlement.setCreatedBy(creditor);
            settlements.add(settlement);

            cred.balance -= amount;
            debt.balance += amount; // debt.balance is negative

            if (cred.balance > 0.0001) creditors.add(cred);
            if (debt.balance < -0.0001) debtors.add(debt);
        }

        return settlements;
    }
}

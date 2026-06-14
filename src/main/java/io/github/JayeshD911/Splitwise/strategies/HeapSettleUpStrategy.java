package io.github.JayeshD911.Splitwise.strategies;

import io.github.JayeshD911.Splitwise.models.Expense;
import io.github.JayeshD911.Splitwise.models.ExpenseUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HeapSettleUpStrategy implements SettleUpStrategy {

    @Override
    public List<Expense> settleUp(List<ExpenseUser> expenses) {
        System.out.println("Settling up using HeapSettleUpStrategy");
        return null;
    }
}

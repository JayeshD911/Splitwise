package io.github.JayeshD911.Splitwise.strategies;

import io.github.JayeshD911.Splitwise.models.Expense;
import io.github.JayeshD911.Splitwise.models.ExpenseUser;
import io.github.JayeshD911.Splitwise.models.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeapSettleUpStrategyTest {

    @Test
    void settlesSingleExpenseIntoExpectedTransfers() {
        HeapSettleUpStrategy strategy = new HeapSettleUpStrategy();

        User a = new User();
        a.setId(1L);
        User b = new User();
        b.setId(2L);
        User c = new User();
        c.setId(3L);

        Expense expense = new Expense();
        expense.setId(100L);
        expense.setAmount(100.0);
        expense.setCreatedBy(a);

        ExpenseUser euA = new ExpenseUser();
        euA.setExpense(expense);
        euA.setUser(a);
        euA.setAmount(20);

        ExpenseUser euB = new ExpenseUser();
        euB.setExpense(expense);
        euB.setUser(b);
        euB.setAmount(40);

        ExpenseUser euC = new ExpenseUser();
        euC.setExpense(expense);
        euC.setUser(c);
        euC.setAmount(40);

        List<Expense> settlements = strategy.settleUp(List.of(euA, euB, euC));

        assertEquals(2, settlements.size());
        assertTrue(settlements.stream().allMatch(e -> e.getAmount() != null && e.getAmount() == 40.0));
        assertTrue(settlements.stream().allMatch(e -> e.getCreatedBy() != null && e.getCreatedBy().getId().equals(1L)));
    }
}


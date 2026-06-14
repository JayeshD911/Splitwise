package io.github.JayeshD911.Splitwise.strategies;

import io.github.JayeshD911.Splitwise.models.Expense;
import io.github.JayeshD911.Splitwise.models.ExpenseUser;

import java.util.List;

public interface SettleUpStrategy {

    List<Expense> settleUp(List<ExpenseUser> expenseUsers);
}

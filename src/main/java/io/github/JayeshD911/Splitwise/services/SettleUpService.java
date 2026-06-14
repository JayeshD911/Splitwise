package io.github.JayeshD911.Splitwise.services;

import io.github.JayeshD911.Splitwise.exceptions.UserNotFoundException;
import io.github.JayeshD911.Splitwise.models.Expense;
import io.github.JayeshD911.Splitwise.models.ExpenseUser;
import io.github.JayeshD911.Splitwise.models.User;
import io.github.JayeshD911.Splitwise.repos.ExpenseUserRepo;
import io.github.JayeshD911.Splitwise.repos.UserRepo;
import io.github.JayeshD911.Splitwise.strategies.HeapSettleUpStrategy;
import io.github.JayeshD911.Splitwise.strategies.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SettleUpService {
    private final ExpenseUserRepo expenseUserRepo;
    private UserRepo userRepo;
    private SettleUpStrategy settleUpStrategy;

    public SettleUpService(UserRepo userRepo, ExpenseUserRepo expenseUserRepo) {
        this.userRepo = userRepo;
        this.expenseUserRepo = expenseUserRepo;
        this.settleUpStrategy = new HeapSettleUpStrategy();
    }

    public List<Expense> settleUpUser(Long UserId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepo.findById(UserId);

        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("Invalid User ID");
        }

        User user = optionalUser.get();

        // fetch all the expenses where this user is involved
        List<ExpenseUser>  expenseUsers = expenseUserRepo.findByUser(user);
        Set<ExpenseUser> expensesToSettle = new HashSet<>();
        for (ExpenseUser expenseUser : expenseUsers) {
            expensesToSettle.add(expenseUser);
        }

        //Strategy to settle up:
        List<Expense> transactions = settleUpStrategy.settleUp(expensesToSettle.stream().toList());

        return transactions;

    }

    public List<Expense>  settleUpGroup() {
        return null;
    }
}

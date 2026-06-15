package io.github.JayeshD911.Splitwise.services;

import io.github.JayeshD911.Splitwise.exceptions.UserNotFoundException;
import io.github.JayeshD911.Splitwise.models.Expense;
import io.github.JayeshD911.Splitwise.models.ExpenseUser;
import io.github.JayeshD911.Splitwise.models.Group;
import io.github.JayeshD911.Splitwise.models.User;
import io.github.JayeshD911.Splitwise.repos.ExpenseUserRepo;
import io.github.JayeshD911.Splitwise.repos.GroupRepo;
import io.github.JayeshD911.Splitwise.repos.UserRepo;
import io.github.JayeshD911.Splitwise.strategies.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SettleUpService {
    private final ExpenseUserRepo expenseUserRepo;
    private final UserRepo userRepo;
    private final GroupRepo groupRepo;
    private final SettleUpStrategy settleUpStrategy;

    public SettleUpService(UserRepo userRepo, ExpenseUserRepo expenseUserRepo, GroupRepo groupRepo, SettleUpStrategy settleUpStrategy) {
        this.userRepo = userRepo;
        this.expenseUserRepo = expenseUserRepo;
        this.groupRepo = groupRepo;
        this.settleUpStrategy = settleUpStrategy;
    }

    public List<Expense> settleUpUser(Long userId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepo.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("Invalid User ID");
        }

        User user = optionalUser.get();

        // fetch all the expenses where this user is involved
        List<ExpenseUser> expenseUsers = expenseUserRepo.findByUser(user);
        Set<ExpenseUser> expensesToSettle = new HashSet<>(expenseUsers);

        // Strategy to settle up:
        return settleUpStrategy.settleUp(expensesToSettle.stream().toList());

    }

    public List<Expense> settleUpGroup(Long groupId) throws IllegalArgumentException {
        Optional<Group> optionalGroup = groupRepo.findById(groupId);
        if (optionalGroup.isEmpty()) {
            throw new IllegalArgumentException("Invalid Group ID");
        }

        Group group = optionalGroup.get();

        List<ExpenseUser> expenseUsers = expenseUserRepo.findByExpense_Group(group);
        Set<ExpenseUser> expensesToSettle = new HashSet<>(expenseUsers);

        return settleUpStrategy.settleUp(expensesToSettle.stream().toList());
    }
}

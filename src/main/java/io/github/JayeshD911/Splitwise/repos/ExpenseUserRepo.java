package io.github.JayeshD911.Splitwise.repos;

import io.github.JayeshD911.Splitwise.models.ExpenseUser;
import io.github.JayeshD911.Splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseUserRepo extends JpaRepository<ExpenseUser, String> {
     void addExpenseUser(String expenseId, String userId, String amount, String expenseUserType);

    List<ExpenseUser> findByUser(User user);

}

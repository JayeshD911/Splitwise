package io.github.JayeshD911.Splitwise.repos;

import io.github.JayeshD911.Splitwise.models.ExpenseUser;
import io.github.JayeshD911.Splitwise.models.Group;
import io.github.JayeshD911.Splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseUserRepo extends JpaRepository<ExpenseUser, Long> {

    List<ExpenseUser> findByUser(User user);

    List<ExpenseUser> findByExpense_Group(Group group);

}

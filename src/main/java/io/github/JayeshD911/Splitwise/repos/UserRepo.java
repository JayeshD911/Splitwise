package io.github.JayeshD911.Splitwise.repos;

import io.github.JayeshD911.Splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}

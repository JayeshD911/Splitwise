package io.github.JayeshD911.Splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExpenseUser extends BaseModel{
    @ManyToOne
    private Expense expense;
    @ManyToOne
    private User user;
    private int amount;
}

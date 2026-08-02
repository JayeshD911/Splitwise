package io.github.JayeshD911.Splitwise.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "expenses")
public class Expense extends BaseModel{
    private String description;
    private Double amount;
    @ManyToOne
    private User createdBy;
    @ManyToOne
    private Group group;
    @OneToMany
    private List<ExpenseUser> expenseUsers;
    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;
}

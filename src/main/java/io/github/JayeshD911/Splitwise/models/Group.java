package io.github.JayeshD911.Splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "groups_table")
public class Group extends BaseModel{
    private String name;
    @ManyToOne
    private User createdBy;
    @ManyToMany
    private List<User> members;
    @OneToMany
    private List<Expense> expenses;
}

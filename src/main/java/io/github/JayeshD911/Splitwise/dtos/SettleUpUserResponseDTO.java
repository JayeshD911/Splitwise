package io.github.JayeshD911.Splitwise.dtos;

import io.github.JayeshD911.Splitwise.models.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpUserResponseDTO {
	private List<Expense> transactions;
	private String message;
	private boolean success;
}

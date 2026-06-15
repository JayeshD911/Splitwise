package io.github.JayeshD911.Splitwise.commands;

import io.github.JayeshD911.Splitwise.controllers.SettleUpController;
import io.github.JayeshD911.Splitwise.dtos.SettleUpUserRequestDTO;

public class SettleUpUserCommand implements Command {

    private final SettleUpController settleUpController;

    public SettleUpUserCommand(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean mathch(String commandString) {
        return commandString.startsWith("settle_up_user");
    }

    @Override
    public void execute(String commandString) {
        String[] commandParts = commandString.split(" ");
        if (commandParts.length != 2) {
            System.out.println("Invalid command format. Usage: settle_up_user <user_id>");
            return;
        }
        Long userId = Long.parseLong(commandParts[1]);
        SettleUpUserRequestDTO req = new SettleUpUserRequestDTO();
        req.setUserId(userId);
        System.out.println(settleUpController.settleUpUser(req).getMessage());

    }
}

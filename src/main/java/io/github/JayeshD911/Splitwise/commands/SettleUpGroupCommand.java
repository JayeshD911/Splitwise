package io.github.JayeshD911.Splitwise.commands;

import io.github.JayeshD911.Splitwise.controllers.SettleUpController;
import io.github.JayeshD911.Splitwise.dtos.SettleUpGroupRequestDTO;

public class SettleUpGroupCommand implements Command {

    private final SettleUpController settleUpController;

    public SettleUpGroupCommand(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean mathch(String commandString) {
        return commandString.startsWith("settle_up_group");
    }

    @Override
    public void execute(String commandString) {
        String[] commandParts = commandString.split(" ");
        if (commandParts.length != 2) {
            System.out.println("Invalid command format. Usage: settle_up_group <group_id>");
            return;
        }
        Long groupId = Long.parseLong(commandParts[1]);
        SettleUpGroupRequestDTO req = new SettleUpGroupRequestDTO();
        req.setGroupId(groupId);
        System.out.println(settleUpController.settleUpGroup(req).getMessage());
    }
}

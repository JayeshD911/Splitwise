package io.github.JayeshD911.Splitwise.commands;

public class SettleUpGroupCommand implements Command {
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
        String groupId = commandParts[1];
        // Call the service method to settle up the group
        // For example: splitwiseService.settleUpGroup(groupId);
        System.out.println("Settling up group with ID: " + groupId);
    }
}

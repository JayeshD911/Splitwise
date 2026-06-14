package io.github.JayeshD911.Splitwise.commands;

public class SettleUpUserCommand implements Command {
    @Override
    public boolean mathch(String commandString) {
        return commandString.startsWith("settle_up_user");
    }

    @Override
    public void execute(String commandString) {
        String[] commandParts = commandString.split(" ");
        if (commandParts.length != 2) {
            System.out.println("Invalid command format. Usage: settle_up_user <user_id>");

        }
        String userId = commandParts[1];
        // Logic to settle up the user's expenses goes here
        System.out.println("Settling up expenses for user: " + userId);

    }
}

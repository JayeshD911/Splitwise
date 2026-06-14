package io.github.JayeshD911.Splitwise.commands;

import java.util.List;

public class CommandExecutor {
    private List<Command> commands;
    private RegisterUserCommand registerUserCommand;
    private SettleUpGroupCommand settleUpGroupCommand;
    private SettleUpUserCommand settleUpUserCommand;

    public CommandExecutor(
            RegisterUserCommand registerUserCommand,
            SettleUpGroupCommand settleUpGroupCommand,
            SettleUpUserCommand settleUpUserCommand
            )
    {
        this.registerUserCommand = registerUserCommand;
        this.settleUpGroupCommand = settleUpGroupCommand;
        this.settleUpUserCommand = settleUpUserCommand;
    }

        public void addCommand(Command command) {
            commands.add(command);
        }

        public void removeCommand(Command command) {
            commands.remove(command);
        }

    public void execute(String commandString) {
        for (Command command : commands) {
            if (command.mathch(commandString)) {
                command.execute(commandString);
                return;
            }
        }
    }

}


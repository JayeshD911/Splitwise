package io.github.JayeshD911.Splitwise.commands;

import java.util.List;
import java.util.ArrayList;

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
        this.commands = new ArrayList<>();
        // register provided commands
        if (registerUserCommand != null) this.commands.add(registerUserCommand);
        if (settleUpGroupCommand != null) this.commands.add(settleUpGroupCommand);
        if (settleUpUserCommand != null) this.commands.add(settleUpUserCommand);
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


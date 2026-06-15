package io.github.JayeshD911.Splitwise.commands;

import io.github.JayeshD911.Splitwise.controllers.RegisterUserController;
import io.github.JayeshD911.Splitwise.dtos.RegisterUserRequestDTO;
import io.github.JayeshD911.Splitwise.dtos.RegisterUserResponseDTO;

import java.util.Arrays;
import java.util.List;

public class RegisterUserCommand implements Command {
    private RegisterUserController registerUserController;

    public RegisterUserCommand(RegisterUserController registerUserController) {
        this.registerUserController = registerUserController;
    }

    //Register <user_name> <Phone_number> <password>
    @Override
    public boolean mathch(String commandString) {
        List<String> tokens = Arrays.asList(commandString.split(" "));
        return tokens.size() == 4 && tokens.get(0).equalsIgnoreCase("register");

    }

    @Override
    public void execute(String commandString) {

        List<String> tokens = Arrays.asList(commandString.split(" "));
        String userName = tokens.get(1);
        String phoneNumber = tokens.get(2);
        String password = tokens.get(3);

        //Call register user controller
        RegisterUserRequestDTO requestDTO = new RegisterUserRequestDTO();
        requestDTO.setName(userName);
        requestDTO.setPhoneNumber(phoneNumber);
        requestDTO.setPassword(password);

        RegisterUserResponseDTO response = registerUserController.registerUser(requestDTO);

    }
}

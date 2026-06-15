package io.github.JayeshD911.Splitwise.controllers;

import io.github.JayeshD911.Splitwise.dtos.RegisterUserRequestDTO;
import io.github.JayeshD911.Splitwise.dtos.RegisterUserResponseDTO;
import io.github.JayeshD911.Splitwise.models.User;
import io.github.JayeshD911.Splitwise.repos.UserRepo;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterUserController {

    private final UserRepo userRepo;

    public RegisterUserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public RegisterUserResponseDTO registerUser(RegisterUserRequestDTO requestDTO) {
        RegisterUserResponseDTO resp = new RegisterUserResponseDTO();
        try {
            User user = new User();
            user.setName(requestDTO.getName());
            user.setPhoneNumber(requestDTO.getPhoneNumber());
            user.setPassword(requestDTO.getPassword());

            User savedUser = userRepo.save(user);
            resp.setUser(savedUser);
            resp.setSuccess(true);
            resp.setMessage("User registered successfully");
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMessage(e.getMessage());
        }
        return resp;
    }
}

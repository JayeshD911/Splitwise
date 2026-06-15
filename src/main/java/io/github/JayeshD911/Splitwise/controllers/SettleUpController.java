package io.github.JayeshD911.Splitwise.controllers;

import io.github.JayeshD911.Splitwise.dtos.SettleUpGroupRequestDTO;
import io.github.JayeshD911.Splitwise.dtos.SettleUpGroupResponseDTO;
import io.github.JayeshD911.Splitwise.dtos.SettleUpUserRequestDTO;
import io.github.JayeshD911.Splitwise.dtos.SettleUpUserResponseDTO;
import io.github.JayeshD911.Splitwise.services.SettleUpService;
import org.springframework.stereotype.Controller;

@Controller
public class SettleUpController {

    private final SettleUpService settleUpService;

    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleUpUserResponseDTO settleUpUser(SettleUpUserRequestDTO requestDTO) {
        SettleUpUserResponseDTO resp = new SettleUpUserResponseDTO();
        try {
            resp.setTransactions(settleUpService.settleUpUser(requestDTO.getUserId()));
            resp.setSuccess(true);
            resp.setMessage("Settlements calculated");
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    public SettleUpGroupResponseDTO settleUpGroup(SettleUpGroupRequestDTO requestDTO) {
        SettleUpGroupResponseDTO resp = new SettleUpGroupResponseDTO();
        try {
            resp.setTransactions(settleUpService.settleUpGroup(requestDTO.getGroupId()));
            resp.setSuccess(true);
            resp.setMessage("Settlements calculated");
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMessage(e.getMessage());
        }
        return resp;
    }
}

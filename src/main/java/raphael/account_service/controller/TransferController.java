package raphael.account_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import raphael.account_service.dto.Accounts;
import raphael.account_service.dto.CreateAccountRquest;
import raphael.account_service.dto.CreateTransferRequest;
import raphael.account_service.dto.Transfers;
import raphael.account_service.service.TransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping
    public List<Transfers> getTransfers() {
        return transferService.findAllTransfers();
    }

    @PostMapping("")
    public Transfers postTranfer(@Valid @RequestBody CreateTransferRequest body) {
        return transferService.createTransfer(body);
    }

    @PostMapping("/account")
    public Accounts postCreateAccount(@RequestBody CreateAccountRquest body) {
        return transferService.createAccount(body);
    }

    @DeleteMapping("/account/{id}")
    public String deleteAccount(@PathVariable Long id) {
        return transferService.deleteAccount(id);
    }

}

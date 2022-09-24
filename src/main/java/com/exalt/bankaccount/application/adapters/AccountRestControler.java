package com.exalt.bankaccount.application.adapters;

import com.exalt.bankaccount.application.adapters.dto.DepositRequest;
import com.exalt.bankaccount.application.adapters.dto.TransationResponse;
import com.exalt.bankaccount.application.adapters.dto.WithdrawRequest;
import com.exalt.bankaccount.domain.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountRestControler {
    private final BankAccountService bankAccountService;

    public AccountRestControler(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping(value = "/deposit")
    @ResponseStatus(HttpStatus.OK)
    public void deposit(@RequestBody DepositRequest depositRequest) {
        bankAccountService.deposit(depositRequest.id, depositRequest.amount);
    }

    @PostMapping(value = "/withdraw")
    @ResponseStatus(HttpStatus.OK)
    public void withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        bankAccountService.withdraw(withdrawRequest.id, withdrawRequest.amount);
    }

    @GetMapping(value = "/{id}/history")
    @ResponseStatus(HttpStatus.OK)
    public List<TransationResponse> getHistory(@PathVariable("id") Long id) {
        return bankAccountService.getHistoryForAccount(id).stream()
                .map(TransationResponse::of)
                .toList();
    }
}

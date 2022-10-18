package com.exalt.bankaccount.adapters.in.api;

import com.exalt.bankaccount.adapters.in.dto.DepositRequest;
import com.exalt.bankaccount.adapters.in.dto.TransactionResponse;
import com.exalt.bankaccount.adapters.in.dto.WithdrawRequest;
import com.exalt.bankaccount.application.ports.in.DepositUseCase;
import com.exalt.bankaccount.application.ports.in.HistoryUseCase;
import com.exalt.bankaccount.application.ports.in.WithdrawUseCase;
import com.exalt.bankaccount.domain.exception.NegativeBalanceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {
    @Qualifier("HistoryUseCase")
    private final HistoryUseCase historyService;
    @Qualifier("WithdrawUseCase")
    private final WithdrawUseCase withdrawService;
    @Qualifier("DepositUseCase")
    private final DepositUseCase depositService;

    public AccountRestController(HistoryUseCase historyService, WithdrawUseCase withdrawService, DepositUseCase depositService) {
        this.historyService = historyService;
        this.withdrawService = withdrawService;
        this.depositService = depositService;
    }


    @PostMapping(value = "/deposit")
    @ResponseStatus(HttpStatus.OK)
    public void deposit(@RequestBody DepositRequest depositRequest) {
        depositService.deposit(depositRequest.id, depositRequest.amount);
    }

    @PostMapping(value = "/withdraw")
    @ResponseStatus(HttpStatus.OK)
    public void withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        try {
            withdrawService.withdraw(withdrawRequest.id, withdrawRequest.amount);
        }
        catch (NegativeBalanceException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @GetMapping(value = "/{id}/history")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponse> getHistory(@PathVariable("id") Long id) {
        return historyService.getHistoryForAccount(id).stream()
                .map(TransactionResponse::of)
                .toList();
    }
}

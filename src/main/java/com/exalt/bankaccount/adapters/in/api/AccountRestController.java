package com.exalt.bankaccount.adapters.in.api;

import com.exalt.bankaccount.adapters.in.dto.DepositRequest;
import com.exalt.bankaccount.adapters.in.dto.TransactionResponse;
import com.exalt.bankaccount.adapters.in.dto.WithdrawRequest;
import com.exalt.bankaccount.application.ports.in.DepositUseCase;
import com.exalt.bankaccount.application.ports.in.HistoryUseCase;
import com.exalt.bankaccount.application.ports.in.WithdrawUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountRestController {
    private final HistoryUseCase historyService;
    private final WithdrawUseCase withdrawService;
    private final DepositUseCase depositService;

    @PostMapping(value = "/deposit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deposit(@RequestBody DepositRequest depositRequest) {
        depositService.deposit(depositRequest.getId(), depositRequest.getAmount());
    }

    @PostMapping(value = "/withdraw")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        withdrawService.withdraw(withdrawRequest.getId(), withdrawRequest.getAmount());
    }

    @GetMapping(value = "/{id}/history")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponse> getHistory(@PathVariable("id") Long id) {
        return historyService.getHistoryForAccount(id).stream()
                .map(TransactionResponse::of)
                .toList();
    }
}

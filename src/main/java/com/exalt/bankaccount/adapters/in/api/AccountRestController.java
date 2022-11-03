package com.exalt.bankaccount.adapters.in.api;

import com.exalt.bankaccount.adapters.in.dto.*;
import com.exalt.bankaccount.application.converter.MapperTool;
import com.exalt.bankaccount.application.ports.in.CreateAccountUseCase;
import com.exalt.bankaccount.application.ports.in.DepositUseCase;
import com.exalt.bankaccount.application.ports.in.HistoryUseCase;
import com.exalt.bankaccount.application.ports.in.WithdrawUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountRestController {
    private final HistoryUseCase historyService;
    private final WithdrawUseCase withdrawService;
    private final DepositUseCase depositService;
    private final CreateAccountUseCase createService;
    private final MapperTool mapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse create(@RequestBody CreateAccountRequest createAccountRequest) {
        return createService.create(createAccountRequest);
    }

    @PostMapping(value = "{id}/deposit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deposit(@PathVariable("id") Long id, @RequestBody DepositRequest depositRequest) {
        depositService.deposit(id, depositRequest.getAmount());
    }

    @PostMapping(value = "{id}/withdraw")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(@PathVariable("id")Long id, @RequestBody WithdrawRequest withdrawRequest) {
        withdrawService.withdraw(id, withdrawRequest.getAmount());
    }

    @GetMapping(value = "/{id}/history")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponse> getHistory(@PathVariable("id") Long id) {
        return  historyService.getHistoryForAccount(id).stream()
                .map(mapper::transactionToTransactionResponse)
                .toList();
    }
}

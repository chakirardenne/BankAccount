package com.exalt.bankaccount.application.adapters;

import com.exalt.bankaccount.adapters.in.api.AccountRestController;
import com.exalt.bankaccount.adapters.in.dto.CreateAccountRequest;
import com.exalt.bankaccount.adapters.in.dto.CreateAccountResponse;
import com.exalt.bankaccount.adapters.in.dto.DepositRequest;
import com.exalt.bankaccount.adapters.in.dto.WithdrawRequest;
import com.exalt.bankaccount.application.converter.MapperTool;
import com.exalt.bankaccount.application.ports.in.CreateAccountUseCase;
import com.exalt.bankaccount.application.ports.in.DepositUseCase;
import com.exalt.bankaccount.application.ports.in.HistoryUseCase;
import com.exalt.bankaccount.application.ports.in.WithdrawUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = AccountRestController.class)
class AccountRestControllerTest {
    @MockBean
    private HistoryUseCase historyService;
    @MockBean
    private DepositUseCase depositService;
    @MockBean
    private WithdrawUseCase withdrawService;
    @MockBean
    private CreateAccountUseCase createService;
    @MockBean
    private MapperTool mapperTool;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mvc;
    private final String BASE_URL = "/account/";

    @Test
    void deposit() throws Exception {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(100);

        mvc.perform(post(BASE_URL + "1/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(depositRequest)))
                .andExpect(status().isNoContent());
    }

    @Test
    void withdraw() throws Exception {
        WithdrawRequest withdrawRequest = new WithdrawRequest();
        withdrawRequest.setAmount(100);

        mvc.perform(post(BASE_URL + "1/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(withdrawRequest)))
                .andExpect(status().isNoContent());
    }

    @Test
    void getHistory() throws Exception {
        mvc.perform(get(BASE_URL + "1/history")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void create() throws Exception {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setBalance(50.0);
        createAccountRequest.setName("account_test");
        CreateAccountResponse createAccountResponse = new CreateAccountResponse();
        createAccountResponse.setBalance(createAccountRequest.getBalance());
        createAccountResponse.setId(1L);
        createAccountResponse.setName(createAccountRequest.getName());

        when(createService.create(createAccountRequest)).thenReturn(createAccountResponse);

        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createAccountRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(createAccountResponse.getId()))
                .andExpect(jsonPath("$.balance").value(createAccountResponse.getBalance()))
                .andExpect(jsonPath("$.name").value(createAccountResponse.getName()));
    }
}
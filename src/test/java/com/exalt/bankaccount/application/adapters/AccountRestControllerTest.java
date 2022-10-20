package com.exalt.bankaccount.application.adapters;

import com.exalt.bankaccount.adapters.in.api.AccountRestController;
import com.exalt.bankaccount.adapters.in.dto.DepositRequest;
import com.exalt.bankaccount.adapters.in.dto.WithdrawRequest;
import com.exalt.bankaccount.application.ports.in.DepositUseCase;
import com.exalt.bankaccount.application.ports.in.HistoryUseCase;
import com.exalt.bankaccount.application.ports.in.WithdrawUseCase;
import com.exalt.bankaccount.domain.intf.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountRestController.class)
class AccountRestControllerTest {
    @MockBean
    private HistoryUseCase historyService;
    @MockBean
    private DepositUseCase depositService;
    @MockBean
    private WithdrawUseCase withdrawService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mvc;
    private Account account;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void deposit() throws Exception {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setId(1L);
        depositRequest.setAmount(100);

        mvc.perform(post("/accounts/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(depositRequest)))
                .andExpect(status().isNoContent());
    }

    @Test
    void withdraw() throws Exception {
        WithdrawRequest withdrawRequest = new WithdrawRequest();
        withdrawRequest.setId(1L);
        withdrawRequest.setAmount(100);

        mvc.perform(post("/accounts/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(withdrawRequest)))
                .andExpect(status().isNoContent());
    }

    @Test
    void getHistory() throws Exception {
        mvc.perform(get("/accounts/1/history")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
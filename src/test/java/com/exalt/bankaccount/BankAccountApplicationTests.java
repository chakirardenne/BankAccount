package com.exalt.bankaccount;

import com.exalt.bankaccount.adapters.in.dto.CreateAccountRequest;
import com.exalt.bankaccount.adapters.in.dto.DepositRequest;
import com.exalt.bankaccount.adapters.in.dto.WithdrawRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BankAccountApplicationTests {
    private static final String BASE_URL = "/account/";
    private static final String DEPOSIT_URL = BASE_URL + "/{id}/deposit";
    private static final String WITHDRAW_URL = BASE_URL + "/{id}/withdraw";
    private static final String HISTORY_URL = BASE_URL + "/{id}/history";
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    MockMvc mvc;

    @Test
    void contextLoads() {
    }

    @Test
    void testFull() throws Exception {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setBalance(50.0);
        createAccountRequest.setName("account_test");

        mvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAccountRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.balance").value(createAccountRequest.getBalance()))
                .andExpect(jsonPath("$.name").value(createAccountRequest.getName()));
        WithdrawRequest withdrawRequest = new WithdrawRequest();
        withdrawRequest.setAmount(20);
        mvc.perform(post(WITHDRAW_URL, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(withdrawRequest)))
                .andExpect(status().isNoContent());
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAmount(70);
        mvc.perform(post(DEPOSIT_URL, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(depositRequest)))
                .andExpect(status().isNoContent());
        mvc.perform(get(HISTORY_URL, 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[-1].balance").value(100));
    }
}
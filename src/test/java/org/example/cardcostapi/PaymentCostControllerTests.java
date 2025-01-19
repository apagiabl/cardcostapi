package org.example.cardcostapi;

import org.example.cardcostapi.repository.ClearingCostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentCostControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClearingCostRepository repository;

    @BeforeEach
    void setup() {
        //repository.save(new ClearingCost( "US", 5.0));
        //repository.save(new ClearingCost( "Others", 10.0));
    }

    @Test
    void testCalculateClearingCost() throws Exception {
        String json = """
                {
                    "card_number": "45717360"
                }
                """;

        mockMvc.perform(post("/api/payment-cards-cost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country").value("DK"))
                .andExpect(jsonPath("$.cost").value(35.0));
    }
}
package org.example.cardcostapi;

import org.example.cardcostapi.model.ClearingCost;
import org.example.cardcostapi.repository.ClearingCostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClearingCostControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClearingCostRepository repository;

    @Test
    void testCreateClearingCost() throws Exception {
        String json = """
                {
                    "countryCode": "UK",
                    "cost": 55.0
                }
                """;

        mockMvc.perform(post("/api/clearing-costs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.countryCode").value("UK"))
                .andExpect(jsonPath("$.cost").value(55.0));
    }

    @Test
    void testGetAllClearingCosts() throws Exception {
        // Seed the database
        //repository.save(new ClearingCost( "US", 5.0));

        mockMvc.perform(get("/api/clearing-costs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].countryCode").value("UK"))
                .andExpect(jsonPath("$[0].cost").value(55.0));
    }
}

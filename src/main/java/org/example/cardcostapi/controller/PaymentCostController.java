package org.example.cardcostapi.controller;

import org.example.cardcostapi.model.ClearingCost;
import org.example.cardcostapi.service.PaymentCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment-cards-cost")
public class PaymentCostController {

    @Autowired
    private PaymentCostService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> calculateCost(@RequestBody Map<String, String> request) {
        String cardNumber = request.get("card_number");
        if (cardNumber == null || cardNumber.length() < 6) {
            throw new IllegalArgumentException("Invalid card number");
        }

        String countryCode = service.getCardCountry(cardNumber);
        ClearingCost cost = service.calculateClearingCost(countryCode);

        Map<String, Object> response = new HashMap<>();
        response.put("country", countryCode);
        response.put("cost", cost.getCost());

        return ResponseEntity.ok(response);
    }
}

package org.example.cardcostapi.service;

import org.example.cardcostapi.model.ClearingCost;
import org.example.cardcostapi.repository.ClearingCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class PaymentCostService {

    @Autowired
    private ClearingCostRepository repository;

    public String getCardCountry(String cardNumber) {
        // Get BIN (first 6 digits of the card)
        String bin = cardNumber.substring(0, 6);
        String url = "https://lookup.binlist.net/" + cardNumber;

        RestTemplate restTemplate = new RestTemplate();
        Map binData = restTemplate.getForObject(url, Map.class);
        String country = (String) ((Map) binData.get("country")).get("alpha2");

        return country;
    }

    public ClearingCost calculateClearingCost(String countryCode) {
        return repository.findByCountryCode(countryCode)
                .orElseThrow(() -> new RuntimeException("Default cost not configured"));
    }
}

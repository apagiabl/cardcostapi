package org.example.cardcostapi.controller;

import org.example.cardcostapi.model.ClearingCost;
import org.example.cardcostapi.service.ClearingCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clearing-costs")
public class ClearingCostController {

    @Autowired
    private ClearingCostService service;

    @PostMapping
    public ResponseEntity<ClearingCost> createCost(@RequestBody ClearingCost cost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createClearingCost(cost));
    }

    @GetMapping
    public ResponseEntity<List<ClearingCost>> getAllCosts() {
        return ResponseEntity.ok(service.getAllClearingCosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClearingCost> getCostById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getClearingCostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClearingCost> updateCost(@PathVariable Long id, @RequestBody ClearingCost cost) {
        return ResponseEntity.ok(service.updateClearingCost(id, cost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCost(@PathVariable Long id) {
        service.deleteClearingCost(id);
        return ResponseEntity.noContent().build();
    }
}

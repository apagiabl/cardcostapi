package org.example.cardcostapi.service;

import org.example.cardcostapi.exception.ResourceNotFoundException;
import org.example.cardcostapi.model.ClearingCost;
import org.example.cardcostapi.repository.ClearingCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClearingCostService {

    @Autowired
    private ClearingCostRepository repository;

    public ClearingCost createClearingCost(ClearingCost clearingCost) {
        return repository.save(clearingCost);
    }

    public List<ClearingCost> getAllClearingCosts() {
        return repository.findAll();
    }

    public ClearingCost getClearingCostById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ClearingCost not found with id " + id));
    }

    public ClearingCost updateClearingCost(Long id, ClearingCost updatedCost) {
        ClearingCost existingCost = getClearingCostById(id);
        existingCost.setCountryCode(updatedCost.getCountryCode());
        existingCost.setCost(updatedCost.getCost());
        return repository.save(existingCost);
    }

    public void deleteClearingCost(Long id) {
        repository.deleteById(id);
    }
}

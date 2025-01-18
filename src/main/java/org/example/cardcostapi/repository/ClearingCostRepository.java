package org.example.cardcostapi.repository;

import org.example.cardcostapi.model.ClearingCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClearingCostRepository extends JpaRepository<ClearingCost, Long> {
    Optional<ClearingCost> findByCountryCode(String countryCode);
}

package org.example.cardcostapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clearing_cost_matrix")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClearingCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_code", nullable = false, length = 2)
    private String countryCode;

    @Column(name = "cost", nullable = false)
    private Double cost;
}

package com.example.demo.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class MarketPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cropName;
    private double pricePerKg;
    private String market;
    private LocalDate updatedDate;

    // getters and setters
}

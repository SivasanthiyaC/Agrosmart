package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
    Buyer findByEmailAndPassword(String email, String password);
}


package com.example.demo.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Farmer;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    Optional<Farmer> findByEmail(String email);

    List<Farmer> findByStatus(String status);
}





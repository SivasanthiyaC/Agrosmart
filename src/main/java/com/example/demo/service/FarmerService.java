package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Farmer;
import com.example.demo.repository.FarmerRepository;

@Service
public class FarmerService {

    @Autowired
    private FarmerRepository farmerRepository;

    // Register farmer (default status = PENDING)
    public Farmer registerFarmer(Farmer farmer) {
        Optional<Farmer> existing = farmerRepository.findByEmail(farmer.getEmail());
        if (existing.isPresent()) {
            return null; // email already exists
        }
        farmer.setStatus("PENDING");
        return farmerRepository.save(farmer);
    }

    // Login logic WITH admin approval check
    public Farmer login(String email, String password) {
        Optional<Farmer> optional = farmerRepository.findByEmail(email);

        if (optional.isEmpty()) {
            return null;
        }

        Farmer farmer = optional.get();

        if (!farmer.getPassword().equals(password)) {
            return null;
        }

        if (!"APPROVED".equalsIgnoreCase(farmer.getStatus())) {
            throw new RuntimeException("ADMIN_APPROVAL_REQUIRED");
        }

        return farmer;
    }

    public Farmer updateFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }
}

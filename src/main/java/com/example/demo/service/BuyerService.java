package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Buyer;
import com.example.demo.repository.BuyerRepository;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    public Buyer login(String email, String password) {
    	System.out.println(email + " " + password);
        return buyerRepository.findByEmailAndPassword(email, password);
    }
}


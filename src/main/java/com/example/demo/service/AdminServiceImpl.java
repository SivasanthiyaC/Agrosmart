package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.BuyerRepository;
import com.example.demo.repository.FarmerRepository;
import com.example.demo.repository.OrderRepository;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final FarmerRepository farmerRepository;
    private final BuyerRepository buyerRepository;
    private final OrderRepository orderRepository;

    public AdminServiceImpl(
            AdminRepository adminRepository,
            FarmerRepository farmerRepository,
            BuyerRepository buyerRepository,
            OrderRepository orderRepository) {

        this.adminRepository = adminRepository;
        this.farmerRepository = farmerRepository;
        this.buyerRepository = buyerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Admin> login(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public long getFarmerCount() {
        return farmerRepository.count();
    }

    @Override
    public long getBuyerCount() {
        return buyerRepository.count();
    }

    @Override
    public long getOrderCount() {
        return orderRepository.count();
    }
    
    
}



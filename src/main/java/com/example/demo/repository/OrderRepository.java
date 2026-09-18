package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByBuyerId(Long buyerId);

    List<Order> findByFarmer_FarmerId(Long farmerId);
    long countByFarmer_FarmerId(Long farmerId);

    long countByFarmer_FarmerIdAndStatus(Long farmerId, String status);

    
}




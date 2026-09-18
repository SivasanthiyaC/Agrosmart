package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.Admin;

public interface AdminService {

    Optional<Admin> login(String email, String password);

    long getFarmerCount();
    long getBuyerCount();
    long getOrderCount();
}

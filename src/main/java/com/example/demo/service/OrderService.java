package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Order;

public interface OrderService {
    void saveOrder(Order order);

    List<Order> getOrdersByFarmerId(Long farmerId);

    void updateOrderStatus(Long orderId, String status);
}



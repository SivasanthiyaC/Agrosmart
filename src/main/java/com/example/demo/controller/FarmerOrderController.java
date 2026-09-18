package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Farmer;
import com.example.demo.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/farmer")
public class FarmerOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String viewOrders(HttpSession session, Model model) {

        Farmer farmer = (Farmer) session.getAttribute("loggedFarmer");
        if (farmer == null) {
            return "redirect:/farmer/login";
        }

        model.addAttribute(
            "orders",
            orderService.getOrdersByFarmerId(farmer.getFarmerId())
        );

        return "farmer/orders";
    }

    @GetMapping("/orders/accept/{id}")
    public String acceptOrder(@PathVariable Long id) {
        orderService.updateOrderStatus(id, "ACCEPTED");
        return "redirect:/farmer/orders";
    }

    @GetMapping("/orders/reject/{id}")
    public String rejectOrder(@PathVariable Long id) {
        orderService.updateOrderStatus(id, "REJECTED");
        return "redirect:/farmer/orders";
    }
}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Buyer;
import com.example.demo.model.Crop;
import com.example.demo.model.Order;
import com.example.demo.repository.CropRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.BuyerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;
    @Autowired
    private CropRepository cropRepository;
    @Autowired
    private OrderRepository orderRepository;



    @GetMapping("/login")
    public String showLogin() {
        return "buyer/login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {

        Buyer buyer = buyerService.login(email, password);

        if (buyer == null) {
            model.addAttribute("error", "Invalid email or password");
            return "buyer/login";
        }

        session.setAttribute("loggedBuyer", buyer);
        return "redirect:/buyer/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if (session.getAttribute("loggedBuyer") == null) {
            return "redirect:/buyer/login";
        }
        return "buyer/dashboard";
    }
    @GetMapping("/crops")
    public String viewCrops(Model model) {
        List<Crop> crops = cropRepository.findAll();
        model.addAttribute("crops", crops);
        return "buyer/view-crops";
    }


  
    @GetMapping("/orders")
    public String myOrders(HttpSession session, Model model) {

        Buyer buyer = (Buyer) session.getAttribute("loggedBuyer");
        if (buyer == null) {
            return "redirect:/buyer/login";
        }

        model.addAttribute(
            "orders",
            orderRepository.findByBuyerId(buyer.getId())
        );

        return "buyer/orders";
    }

    @GetMapping("/order/{id}")
    public String placeOrder(@PathVariable Long id, Model model, HttpSession session) {
        Buyer buyer = (Buyer) session.getAttribute("loggedBuyer");
        if (buyer == null) return "redirect:/buyer/login";

        Crop crop = cropRepository.findById(id).orElse(null);
        model.addAttribute("crop", crop);

        return "buyer/place-order";
    }

    @PostMapping("/order")
    public String saveOrder(
            @RequestParam Long cropId,
            @RequestParam int quantity,
            HttpSession session) {

        Buyer buyer = (Buyer) session.getAttribute("loggedBuyer");
        if (buyer == null) return "redirect:/buyer/login";

        Crop crop = cropRepository.findById(cropId).orElse(null);

        Order order = new Order();
        order.setBuyer(buyer);
        order.setCrop(crop);
        order.setFarmer(crop.getFarmer()); // ⭐ IMPORTANT
        order.setQuantity(quantity);
        order.setPrice(crop.getPrice() * quantity);
        order.setStatus("PENDING");

        orderRepository.save(order);

        return "redirect:/buyer/orders";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();   // destroy session
        return "redirect:/buyer/login";
    }

}
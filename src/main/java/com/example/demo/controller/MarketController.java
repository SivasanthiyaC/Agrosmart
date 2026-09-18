package com.example.demo.controller;


import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/farmer/market")
public class MarketController {

    // Opens the market price page
    @GetMapping
    public String showMarketPricePage(Model model) {
        model.addAttribute("crops", List.of("Groundnut", "Millet", "Rice"));
        return "farmer/market-price"; // ✅ matches market-price.html
    }

    // Handles price check
    @PostMapping("/check")
    public String checkMarketPrice(
            @RequestParam String cropName,
            Model model) {

        double price;

        switch (cropName) {
            case "Groundnut" -> price = 5200;
            case "Millet" -> price = 4100;
            case "Rice" -> price = 3600;
            default -> price = 0;
        }

        model.addAttribute("crop", cropName);
        model.addAttribute("price", price);

        return "farmer/market-result"; // ✅ matches market-result.html
    }
}

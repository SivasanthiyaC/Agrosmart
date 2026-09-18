package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.MarketPrice;
import com.example.demo.repository.MarketPriceRepository;

@Service
public class MarketPriceService {

    @Autowired
    private MarketPriceRepository repo;

    public List<MarketPrice> getPrice(String cropName) {
        return repo.findByCropNameIgnoreCase(cropName);
    }

    public String priceTrend(double price) {
        if (price > 70) return "HIGH 🔺";
        if (price < 40) return "LOW 🔻";
        return "NORMAL ⚖";
    }
}


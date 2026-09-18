package com.example.demo.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.MarketPrice;

public interface MarketPriceRepository 
        extends JpaRepository<MarketPrice, Long> {

    List<MarketPrice> findByCropNameIgnoreCase(String cropName);
}

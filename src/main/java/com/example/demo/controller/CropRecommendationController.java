package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Crop;
import com.example.demo.repository.CropRepository;

@Controller
public class CropRecommendationController {

    @Autowired
    private CropRepository cropRepository;

    @GetMapping("/farmer/recommend")
    public String showRecommendPage() {
        return "recommend-crop";
    }

    @PostMapping("/farmer/recommend")
    public String recommendCrops(
            @RequestParam String soilType,
            @RequestParam String season,
            Model model) {

        List<Crop> crops = cropRepository.findBySoilTypeAndSeason(soilType, season);

        model.addAttribute("crops", crops);
        model.addAttribute("soilType", soilType);
        model.addAttribute("season", season);

        return "recommend-crop";
    }
}

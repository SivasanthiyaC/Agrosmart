package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Crop;
import com.example.demo.model.Farmer;
import com.example.demo.service.CropService;

import jakarta.servlet.http.HttpSession;

@Controller
	@RequestMapping("/farmer/crops")
	public class CropController {

	    @Autowired
	    private CropService cropService;

	    @GetMapping("/add")
	    public String showAddCropForm(HttpSession session, Model model) {

	        Farmer farmer = (Farmer) session.getAttribute("loggedFarmer");
	        if (farmer == null) {
	            return "redirect:/farmer/login";
	        }

	        model.addAttribute("crop", new Crop());
	        return "farmer/add-crop";
	    }

	    @PostMapping("/save")
	    public String saveCrop(@ModelAttribute Crop crop, HttpSession session) {

	        Farmer farmer = (Farmer) session.getAttribute("loggedFarmer");
	        crop.setFarmer(farmer);

	        cropService.saveCrop(crop);
	        return "redirect:/farmer/crops";
	    }
	    
	    @GetMapping
	    public String listCrops(HttpSession session, Model model) {

	        Farmer farmer = (Farmer) session.getAttribute("loggedFarmer");
	        if (farmer == null) {
	            return "redirect:/farmer/login";
	        }

	        List<Crop> crops = cropService.getCropsByFarmer(farmer);
	        model.addAttribute("crops", crops);

	        return "farmer/crops";
	    }
	    
	    @GetMapping("/recommend")
	    public String showRecommendationForm() {
	        return "farmer/recommend-crop";
	    }

	    @PostMapping("/recommend")
	    public String recommendCrops(
	            @RequestParam String soilType,
	            @RequestParam String season,
	            Model model) {

	        List<Crop> crops = cropService.recommendCrops(soilType, season);
	        model.addAttribute("crops", crops);
	        return "farmer/recommend-result";
	    }


	}




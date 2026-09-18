package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Crop;
import com.example.demo.model.Farmer;
import com.example.demo.repository.CropRepository;

@Service
	public class CropService {

	    @Autowired
	    private CropRepository cropRepository;

	    public Crop saveCrop(Crop crop) {
	        return cropRepository.save(crop);
	    }
	    public List<Crop> recommendCrops(String soilType, String season) {

	        List<Crop> recommended = new ArrayList<>();

	        // Sandy Soil
	        if (soilType.equalsIgnoreCase("Sandy")) {
	            if (season.equalsIgnoreCase("Summer")) {
	                recommended.add(createCrop("Groundnut", "Summer", "Sandy"));
	                recommended.add(createCrop("Millet", "Summer", "Sandy"));
	            } else if (season.equalsIgnoreCase("Winter")) {
	                recommended.add(createCrop("Mustard", "Winter", "Sandy"));
	            }
	        }

	        // Clay Soil
	        if (soilType.equalsIgnoreCase("Clay")) {
	            if (season.equalsIgnoreCase("Rainy")) {
	                recommended.add(createCrop("Rice", "Rainy", "Clay"));
	            } else if (season.equalsIgnoreCase("Winter")) {
	                recommended.add(createCrop("Wheat", "Winter", "Clay"));
	            }
	        }

	        // Loamy Soil
	        if (soilType.equalsIgnoreCase("Loamy")) {
	            recommended.add(createCrop("Sugarcane", season, "Loamy"));
	            recommended.add(createCrop("Cotton", season, "Loamy"));
	        }

	        return recommended;
	    }

	    private Crop createCrop(String name, String season, String soil) {
	        Crop crop = new Crop();
	        crop.setName(name);
	        crop.setSeason(season);
	        crop.setSoilType(soil);
	        return crop;
	    }    
	    
	    public List<Crop> getCropsByFarmer(Farmer farmer) {
	        return cropRepository.findByFarmer(farmer);
	    }

	    public void deleteCrop(Long id) {
	        cropRepository.deleteById(id);
	    }
	    public List<Crop> getCropsBySoilAndSeason(String soilType, String season) {
	        return cropRepository.findBySoilTypeAndSeason(soilType, season);
	    }


	}




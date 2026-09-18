package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Crop;
import com.example.demo.model.Farmer;


public interface CropRepository extends JpaRepository<Crop, Long> {

	    List<Crop> findByFarmer(Farmer farmer);
	    List<Crop> findBySoilTypeAndSeason(String soilType, String season);

	}





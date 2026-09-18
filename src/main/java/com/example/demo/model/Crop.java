package com.example.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "crops")
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crop_id")
    private Long cropId;

    private String name;
    private String soilType;
    private String season;
    private double price;
    private String recommendations;

    // Many crops → One farmer
    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;
    
    public Crop() {
        // default constructor (REQUIRED by Spring & Thymeleaf)
    }
    
    public Crop(String name, String soilType, String season) {
        this.name = name;
        this.soilType = soilType;
        this.season = season;
    }


    // ===== Getters & Setters =====

    public Long getCropId() {
        return cropId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getSeason() {
        return season;
    }
    public Crop(String name, String soilType, String season, double price, String recommendations) {
        this.name = name;
        this.soilType = soilType;
        this.season = season;
        this.price = price;
        this.recommendations = recommendations;
    }
  

    public void setSeason(String season) {
        this.season = season;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}

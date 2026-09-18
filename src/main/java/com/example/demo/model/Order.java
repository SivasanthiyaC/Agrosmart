package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


    private String cropName;
    private int quantity;
    private double price;
    private String status; // PENDING, ACCEPTED, REJECTED
    @ManyToOne
    private Buyer buyer;

    @ManyToOne
    private Crop crop;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    // ----- getters & setters -----

    

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }
    public Long getId() {
        return id;
    }

    public Crop getCrop() {
        return crop;
    }

    public Buyer getBuyer() {
        return buyer;
    }

}


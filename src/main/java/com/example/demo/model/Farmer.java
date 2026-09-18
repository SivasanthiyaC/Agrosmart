package com.example.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

	@Entity
	@Table(name = "farmers")
	public class Farmer {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long farmerId;
		

	    @NotBlank(message = "Name is required")
	    private String name;

	    @NotBlank(message = "Email is required")
	    @Email(message = "Invalid email format")
	    @Column(unique = true)
	    private String email;

	    @NotBlank(message = "Password is required")
	    @Size(min = 6, message = "Password must be at least 6 characters")
	    private String password;

	    @NotBlank(message = "Phone number is required")
	    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number")
	    private String phone;

	    @NotBlank(message = "Address is required")
	    private String address;
	    @Column(nullable = false)
	    private String status;

	    
	    // Default constructor
	    public Farmer() {}

	    // Constructor with fields
	    public Farmer(String name, String email, String password, String phone, String address) {
	        this.name = name;
	        this.email = email;
	        this.password = password;
	        this.phone = phone;
	        this.address = address;
	    }

	    // Getters and Setters
	    public Long getFarmerId() { return farmerId; }
	    public void setFarmerId(Long farmerId) { this.farmerId = farmerId; }

	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }

	    public String getEmail() { return email; }
	    public void setEmail(String email) { this.email = email; }

	    public String getPassword() { return password; }
	    public void setPassword(String password) { this.password = password; }

	    public String getPhone() { return phone; }
	    public void setPhone(String phone) { this.phone = phone; }

	    public String getAddress() { return address; }
	    public void setAddress(String address) { this.address = address; }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    }






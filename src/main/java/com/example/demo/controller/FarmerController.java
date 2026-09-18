package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Farmer;
import com.example.demo.repository.FarmerRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.FarmerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/farmer")
public class FarmerController {

	@Autowired
	private FarmerService farmerService;
	 @Autowired
	    private OrderRepository orderRepository;
	 @Autowired
	    private FarmerRepository farmerRepository;


	// Show Registration Page
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("farmer", new Farmer());
		return "farmer/register";
	}

	// Handle Registration Form
	@PostMapping("/register")
	public String registerFarmer(
			@Valid @ModelAttribute Farmer farmer,
			BindingResult result,
			Model model) {
		farmer.setStatus("PENDING");
		farmerRepository.save(farmer);

		if (result.hasErrors()) {
			return "farmer/register";
		}

		Farmer saved = farmerService.registerFarmer(farmer);

		if (saved == null) {
			model.addAttribute("error", "Email already registered");
			return "farmer/register";
		}

		return "redirect:/farmer/login";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {
	    model.addAttribute("farmer", new Farmer());
	    return "farmer/login";
	}

	@PostMapping("/login")
	public String farmerLogin(
	        @RequestParam String email,
	        @RequestParam String password,
	        Model model,
	        HttpSession session) {

	    try {
	        Farmer farmer = farmerService.login(email, password);
	        session.setAttribute("loggedFarmer", farmer);
	        return "redirect:/farmer/dashboard";

	    } catch (RuntimeException e) {
	        if ("ADMIN_APPROVAL_REQUIRED".equals(e.getMessage())) {
	            model.addAttribute("error", "Admin approval required");
	        } else {
	            model.addAttribute("error", "Invalid email or password");
	        }
	        model.addAttribute("farmer", new Farmer());
	        return "farmer/login";
	    }
	}


	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {

	    Farmer farmer = (Farmer) session.getAttribute("loggedFarmer");
	    if (farmer == null) {
	        return "redirect:/farmer/login";
	    }

	    Long farmerId = farmer.getFarmerId();

	    model.addAttribute("totalOrders",
	            orderRepository.countByFarmer_FarmerId(farmerId));

	    model.addAttribute("pendingOrders",
	            orderRepository.countByFarmer_FarmerIdAndStatus(farmerId, "PENDING"));

	    model.addAttribute("acceptedOrders",
	            orderRepository.countByFarmer_FarmerIdAndStatus(farmerId, "ACCEPTED"));

	    model.addAttribute("rejectedOrders",
	            orderRepository.countByFarmer_FarmerIdAndStatus(farmerId, "REJECTED"));

	    return "farmer/dashboard";
	}




	@GetMapping("/profile")
	public String farmerProfile(HttpSession session, Model model) {

		Farmer farmer = (Farmer) session.getAttribute("loggedFarmer");

		if (farmer == null) {
			return "redirect:/farmer/login";
		}

		model.addAttribute("farmer", farmer);
		return "farmer/profile";
	}

	@GetMapping("/edit-profile")
	public String editProfile(HttpSession session, Model model) {
		Farmer farmer = (Farmer) session.getAttribute("loggedFarmer");

		if (farmer == null) {
			return "redirect:/farmer/login";
		}

		model.addAttribute("farmer", farmer);
		return "farmer/edit-profile";
	}

	@PostMapping("/update-profile")
	public String updateProfile(@ModelAttribute Farmer farmer, HttpSession session) {

	    Farmer existing = (Farmer) session.getAttribute("loggedFarmer");

	    // KEEP OLD PASSWORD
	    farmer.setPassword(existing.getPassword());

	    farmerService.updateFarmer(farmer);
	    session.setAttribute("loggedFarmer", farmer);

	    return "redirect:/farmer/profile";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/farmer/login";
	}
	
	
}
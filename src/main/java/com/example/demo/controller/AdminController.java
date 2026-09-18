package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Farmer;
import com.example.demo.repository.BuyerRepository;
import com.example.demo.repository.FarmerRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.AdminService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private FarmerRepository farmerRepository;
	@Autowired
	private BuyerRepository buyerRepository;

	@Autowired
	private OrderRepository orderRepository;

	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	// 🔹 Login Page
	@GetMapping("/login")
	public String loginPage() {
		return "admin/login";
	}

	// 🔹 Login Logic
	@PostMapping("/login")
	public String login(@RequestParam String email,
			@RequestParam String password,
			HttpSession session,
			Model model) {

		return adminService.login(email, password)
				.map(admin -> {
					session.setAttribute("admin", admin);
					return "redirect:/admin/dashboard";
				})
				.orElseGet(() -> {
					model.addAttribute("error", "Invalid email or password");
					return "admin/login";
				});
	}

	// 🔹 Dashboard
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {

		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}

		model.addAttribute("farmerCount", adminService.getFarmerCount());
		model.addAttribute("buyerCount", adminService.getBuyerCount());
		model.addAttribute("orderCount", adminService.getOrderCount());

		return "admin/dashboard";
	}

	// 🔹 Logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/login";
	}

	// farmers
	@GetMapping("/farmers")
	public String viewFarmers(Model model) {
	    model.addAttribute("farmers", farmerRepository.findAll());
	    return "admin/farmers";
	}
	
	@GetMapping("/farmers/approve/{id}")
	public String approveFarmer(@PathVariable Long id) {

	    Farmer farmer = farmerRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Farmer not found"));

	    farmer.setStatus("APPROVED");
	    farmerRepository.save(farmer);

	    return "redirect:/admin/farmers";
	}



	@GetMapping("/farmers/block/{id}")
	public String blockFarmer(@PathVariable Long id) {

	    Farmer farmer = farmerRepository.findById(id).orElse(null);
	    if (farmer != null) {
	        farmer.setStatus("BLOCKED");
	        farmerRepository.save(farmer);
	    }

	    return "redirect:/admin/farmers";
	}


	// Buyers
	@GetMapping("/buyers")
	public String viewBuyers(Model model) {
		model.addAttribute("buyers", buyerRepository.findAll());
		return "admin/buyers";
	}

	// Orders
	@GetMapping("/orders")
	public String viewOrders(Model model) {
		model.addAttribute("orders", orderRepository.findAll());
		return "admin/orders";
	}

}



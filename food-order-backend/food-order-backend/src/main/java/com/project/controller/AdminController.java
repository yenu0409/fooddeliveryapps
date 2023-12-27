package com.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Admin;
import com.project.entity.Customer;
import com.project.entity.Dishes;
import com.project.repository.DishesRepository;
import com.project.service.AdminService;
import com.project.service.CustomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AdminService admin_service;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired 
	DishesRepository dishesRepository;
	
	
	  public AdminController(AdminService adminservice) { 
		super();
	  this.admin_service = adminservice; }
	
	@GetMapping("/show")
	List<Admin> getprojects()
	{
		return this.admin_service.getAdmins();
	}
	@PostMapping("/register")
	public ResponseEntity<Admin> saveAdmin(@Valid @RequestBody Admin admin) {
		System.out.println("admin register " + admin);
		return new ResponseEntity<Admin>(admin_service.saveAdmin(admin), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Admin> loginAdmin(@RequestBody Admin admin)
	{
		return new ResponseEntity<Admin>(admin_service.loginAdmin(admin), HttpStatus.CREATED);
	}
	
	@GetMapping("/customer")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/disheslist")
	public List<Dishes> getAllDishess() {
		// TODO Auto-generated method stub
		return dishesRepository.findAll();
	}
	
	//Delete customer
	@DeleteMapping("customer/{id}")
	public String deleteCustomer(@PathVariable("id") long customerId) {
		customerService.deleteCustomer(customerId);
		//boolean flag = true;
		System.out.println("customer deleted successfully");
		return ("Customer deleted successfully");
		
		//return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}

}

package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Admin;
import com.project.entity.Category;
import com.project.entity.Customer;
import com.project.entity.Dishes;
import com.project.exception.ResourceNotFound;
import com.project.repository.AdminRepository;
import com.project.repository.CustomerRepository;
import com.project.repository.DishesRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository admin_repository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private DishesRepository dishesRepository;
	
	public Admin saveAdmin(Admin admin)
	{
		System.out.println("Admin register service"+admin);
		return this.admin_repository.save(admin);
		
	}
	public List<Admin> getAdmins(){
		return admin_repository.findAll();
		
	}

	public Admin loginAdmin(Admin admin) {
		return this.admin_repository.findByAdminEmailIdAndAdminPassword(admin.adminEmailId, admin.adminPassword)
				.orElseThrow(() -> new ResourceNotFound("Admin ", "Id",
						admin.adminEmailId + "and password " + admin.adminPassword));

	}
	public List<Customer> getAllCustomers(long adminId) {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}
	
	//Show Dishes
	public List<Dishes> getAllDishess() {
		// TODO Auto-generated method stub
		return dishesRepository.findAll();
	}

	//Delete customer
	public void deleteCustomer(long customerId) {
		customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFound("Customer","Id",customerId));
		customerRepository.deleteById(customerId);
		
}
	
}

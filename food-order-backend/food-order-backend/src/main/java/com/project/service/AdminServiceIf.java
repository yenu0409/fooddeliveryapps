package com.project.service;

import java.util.List;

import com.project.entity.Admin;
import com.project.entity.Customer;

public interface AdminServiceIf {
	Admin saveAdmin(Admin admin);//postman
	Admin loginAdmin(Admin admin);
	
	//public List<Product> getAllProducts(long adminId);
	public List<Customer> getAllCustomers(long adminId);
}


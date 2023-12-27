package com.project.service;

import java.util.List;

import com.project.entity.Customer;

public interface CustomerServiceif {

	Customer saveCustomer(Customer customer);
	Customer loginCustomer(Customer customer);
	Customer updateCustomer(Customer customer, long customerId);
	Customer getCustomerById(long customerId);
	List<Customer> getAllCustomers();
	Customer getCustomerByEmail(Customer customer);
	void deleteCustomer(long customerId);

}

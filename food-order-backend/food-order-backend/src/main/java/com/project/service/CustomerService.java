package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.entity.Customer;
import com.project.exception.ResourceNotFound;
import com.project.repository.CustomerRepository;

@Service
public class CustomerService implements CustomerServiceif {
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer getCustomerById(long customerId) {

		return customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFound("Customer", "Id", customerId));
	}

	@Override
	public Customer saveCustomer(Customer customer) {

		return customerRepository.save(customer);
	}

	@Override
	public Customer loginCustomer(Customer customer) {

		return this.customerRepository.findByEmailIDAndPassword(customer.emailID, customer.password).orElseThrow(
				() -> new ResourceNotFound("Customer ", "Id", customer.emailID + " and password " + customer.password));
	}

	public Customer getCustomerByEmail(Customer customer) {
		return this.customerRepository.findByEmailID(customer.emailID)
				.orElseThrow(() -> new ResourceNotFound("Customer ", "Email", customer.emailID));
	}

	@Override
	public Customer updateCustomer(Customer customer, long customerId) {

		Customer existingCustomer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFound("Customer", "Id", customerId));
		existingCustomer.setFirstName(customer.getFirstName());
		existingCustomer.setLastName(customer.getLastName());
		existingCustomer.setPhoneNumber(customer.getPhoneNumber());
		existingCustomer.setEmailID(customer.getEmailID());
		existingCustomer.setPassword(customer.getPassword());
		existingCustomer.setAddress(customer.getAddress());
		existingCustomer.setZipCode(customer.getZipCode());
		customerRepository.save(existingCustomer);
		return existingCustomer;
	}

	@Override
	public List<Customer> getAllCustomers() {

		return customerRepository.findAll();
	}

	@Override
	public void deleteCustomer(long customerId) {
		customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFound("Customer","Id",customerId));
		customerRepository.deleteById(customerId);
		
}
}

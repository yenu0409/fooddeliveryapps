package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Cart;
import com.project.entity.Customer;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	void deleteCartByCustomer(Customer c);
}
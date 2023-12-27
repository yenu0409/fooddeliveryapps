package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.project.entity.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {

	public List<Order> findByCustomerCustomerId(long customerId);
	
	public void deleteByOrderId(long orderId);
}
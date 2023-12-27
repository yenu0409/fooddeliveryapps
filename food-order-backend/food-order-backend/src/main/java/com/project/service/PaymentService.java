package com.project.service;

import java.util.*;

import com.project.entity.Payment;
public interface PaymentService {
	Payment addPayment(Payment payment,long orderId,long customerId);
	List<Payment> getAllPayments();
	Payment getPaymentById(long paymentId);
	void deletePayment(long paymentId);
	public List<Payment> getAllPaymentsByCustomerId(long customerId);

}

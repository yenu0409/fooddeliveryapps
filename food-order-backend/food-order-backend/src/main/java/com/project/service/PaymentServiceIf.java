package com.project.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Customer;
import com.project.entity.Order;
import com.project.entity.Payment;
import com.project.exception.ResourceNotFound;
import com.project.repository.OrderRepository;
import com.project.repository.PaymentRepository;

@Service
public class PaymentServiceIf implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderService orderService;

	public PaymentServiceIf(PaymentRepository paymentRepository, DishesService dishService,
			CustomerService customerService, OrderService orderService) {
		super();
		this.paymentRepository = paymentRepository;

		this.customerService = customerService;
		this.orderService = orderService;

	}

	@Override
	public Payment addPayment(Payment payment, long orderId, long customerId) {

		
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFound("Order", "orderId", orderId));
		payment.setOrderId(orderId);
		payment.setTotalPrice(order.getTotalPrice());
		payment.setPaidDate(LocalDate.now());
		payment.setPaidAmount(order.getTotalPrice());
		if (payment.getTotalPrice() == payment.getPaidAmount()) {
			order.setPaymentStatus("PAID");
			order.setOrderStatus("Delivered");
		} else {

			order.setPaymentStatus("NOT-PAID");
			order.setOrderStatus("payment pending");
		}
		Customer customer = customerService.getCustomerById(customerId);

		payment.setCustomer(customer);

		

		return paymentRepository.save(payment);

	}
	

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	@Override
	public List<Payment> getAllPaymentsByCustomerId(long customerId) {
		return paymentRepository.findByOrderId(customerId);
	}

	@Override
	public Payment getPaymentById(long paymentId) {

		return paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFound("Payement", "Id", paymentId));
	}

	@Override
	public void deletePayment(long paymentId) {
		paymentRepository.findById(paymentId).orElseThrow(() -> new ResourceNotFound("Payement", "Id", paymentId));
		paymentRepository.deleteById(paymentId);

	}

}

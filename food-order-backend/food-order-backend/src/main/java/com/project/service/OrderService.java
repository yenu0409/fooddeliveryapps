package com.project.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.project.entity.Cart;
import com.project.entity.Customer;
import com.project.entity.Order;
import com.project.exception.ResourceNotFound;
import com.project.repository.CartRepository;
import com.project.repository.OrderRepository;


@Transactional
@Service
public class OrderService implements OrderServiceif {
	
	@Autowired
	public OrderRepository orderRepository;
	
	@Autowired
	public DishesService dishService;
	
	@Autowired
	public CartService cartService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CartRepository c;
	
	
public OrderService(OrderRepository orderRepository, DishesService dishService, CartService cartService,
			CustomerService customerService) {
		super();
		this.orderRepository = orderRepository;
		this.dishService = dishService;
		this.customerService = customerService;
	}


@Override
public Order addOrder(Order order,long customerId,long cartId)
    {
	
	Cart cart =cartService.getCartById(cartId) ;
	
	Customer customer=customerService.getCustomerById(customerId);
	
	order.setTotalPrice(order.getMrpPrice() * cart.getQuantity());
	order.setPaymentStatus(order.getPaymentStatus());
	order.setOrderStatus(order.getOrderStatus());
	order.setOrderedDate(order.getOrderedDate());
	order.setMrpPrice(cart.getMrpPrice());
	order.setQuantity(cart.getQuantity());
	
	order.setCustomer(customer);
    
	Order o = orderRepository.save(order);
	c.deleteById(cartId);
	return o;
      }

@Override
public List<Order> getAllOrders() 
{
	SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
	java.util.Date date= new java.util.Date();
	String currentDate=sdf.format(date);
	String [] array=currentDate.split("/");
	int month=Integer.parseInt(array[0]);
	int day=Integer.parseInt(array[1]);
	int year=Integer.parseInt(array[2]);
	java.util.Date d=new java.util.Date(month,day,year);
	System.out.println(d);
	List<Order> orders=orderRepository.findAll();
	System.out.println(orders);
	return orderRepository.findAll();
   }

@Override
public List<Order> getOrderByCustomerId(long customerId) {
	SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
	java.util.Date date= new java.util.Date();
	String currentDate=sdf.format(date);
	String [] array=currentDate.split("/");
	int month=Integer.parseInt(array[0]);
	int day=Integer.parseInt(array[1]);
	int year=Integer.parseInt(array[2]);
	java.util.Date d=new java.util.Date(month,day,year);
	System.out.println(d);
	List<Order> orders=orderRepository.findByCustomerCustomerId(customerId);
	System.out.println(orders);
	return orderRepository.findByCustomerCustomerId(customerId);
}


@Override
public Order updateOrder(Order order, long orderId) {
	Order existingOrder=orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFound("Order","Id",orderId));
	existingOrder.setTotalPrice(order.getMrpPrice());
	existingOrder.setPaymentStatus(order.getPaymentStatus());
	existingOrder.setMrpPrice(order.getMrpPrice());
	existingOrder.setOrderStatus(order.getOrderStatus());
	existingOrder.setCustomer(order.getCustomer());
	existingOrder.setOrderedDate(order.getOrderedDate());
	orderRepository.save(existingOrder);
	return existingOrder;
}



@Override
public void deleteOrder(long orderId) {
	orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFound("Order","Id",orderId));
	orderRepository.deleteById(orderId);
	  
}


@Override
public Order getOrderById(long orderId) {
	// TODO Auto-generated method stub
	
	return orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFound("Order","Id",orderId));
	
}

}
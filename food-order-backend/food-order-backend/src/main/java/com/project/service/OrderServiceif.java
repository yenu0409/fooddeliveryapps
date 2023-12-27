package com.project.service;
import java.util.List;
import com.project.entity.Order;

public interface OrderServiceif {
	Order addOrder(Order order,long customerId,long cartId); 
	Order getOrderById(long orderId);
	Order updateOrder(Order order,long orderId);
	List<Order> getOrderByCustomerId(long customerId);
	List<Order> getAllOrders();
	void deleteOrder(long orderId);

}

package com.project.service;

import java.util.List;

import com.project.entity.Cart;
import com.project.entity.Customer;


public interface CartServiceif {
	Cart addCart(Cart cart,long dishId,long customerId);
	List<Cart> getAllCarts();
	Cart getCartById(long cartId);
	Cart updateCart(Cart cart, long cartId);
	void deleteCart(long cartId);
	void deleteCartByCustomer(Customer c);
	

}

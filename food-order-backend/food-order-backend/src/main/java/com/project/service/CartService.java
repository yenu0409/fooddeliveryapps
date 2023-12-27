package com.project.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.entity.Cart;
import com.project.entity.Customer;
import com.project.entity.Dishes;
import com.project.exception.ResourceNotFound;
import com.project.repository.CartRepository;

//@ComponentScan
@Service
public class CartService implements CartServiceif {
	
	@Autowired
	public CartRepository cartRepository;
	
	@Autowired
	public DishesServiceif dishesService;
	
	@Autowired
	public CustomerService customerService;
	
public CartService(CartRepository cartRepository) {
		super();
		this.cartRepository = cartRepository;
	}

@Override
public Cart addCart(Cart cart, long dishId, long customerId) {
    try {
        Dishes dishes = dishesService.getDishesByDishesId(dishId);
        Customer customer = customerService.getCustomerById(customerId);
        cart.setQuantity(cart.getQuantity()); // set the quantity correctly
        cart.setdish(dishes);
        cart.setMrpPrice(dishes.getMrpPrice());
        cart.setCustomer(customer);
        if (cart.getQuantity() <= 0) {
            return null; // Do not save the cart item to the repository and return null
        } else {
            List<Cart> crl = this.getAllCarts();
            for (Cart c : crl) {
                if (c.getCustomer().getCustomerId() == customerId && c.getdish().getDishId() == dishId) {
                    c.setQuantity(cart.getQuantity() + c.getQuantity());
                    c.setMrpPrice(dishes.getMrpPrice() * c.getQuantity());
                    if (c.getQuantity() <= 0) {
                        // Delete the cart item from the repository
                        cartRepository.delete(c);
                        return null;
                    } else {
                        return this.updateCart(c, c.getCartId());
                    }
                }
            }
            return cartRepository.save(cart);
        }
    } catch (Exception e) {
        // handle any exceptions that may occur
        return null;
    }
}


@Override
public List<Cart> getAllCarts() {
	// TODO Auto-generated method stub
	return cartRepository.findAll();
}

@Override
public Cart getCartById(long cartId) {
	// TODO Auto-generated method stub
	return cartRepository.findById(cartId).orElseThrow(()->new ResourceNotFound("Cart","Id",cartId));
}

@Override
public Cart updateCart(Cart cart, long cartId) {
	Cart existingCart=cartRepository.findById(cartId).orElseThrow(()->new ResourceNotFound("Cart","Id",cartId));
	existingCart.setQuantity(cart.getQuantity());
	//existingCart.setPrice(cart.getPrice());
	existingCart.setMrpPrice(cart.getMrpPrice());
	//existingCart.setImage(cart.getImage());
	existingCart.setCartId(cart.getCartId());
	existingCart.setdish(cart.getdish());
	//existingCart.setCustomerId(cart.getCustomerId());
	existingCart.setCustomer(cart.getCustomer());
    cartRepository.save(existingCart);
    
	return existingCart;
}

@Override
public void deleteCart(long cartId) {
	// TODO Auto-generated method stub
	Cart existingCart=cartRepository.findById(cartId).orElseThrow(()->new ResourceNotFound("Cart","Id",cartId));
	Dishes dishes =dishesService.getDishesByDishesId(existingCart.getdish().getDishId());
	//dishes.setQuantity(dishes.getQuantity() + existingCart.getQuantity());
	dishesService.updateDishes(dishes,dishes.getDishId());
	cartRepository.findById(cartId).orElseThrow(()->new ResourceNotFound("Cart","Id",cartId));
	cartRepository.deleteById(cartId);
	
	
}

@Override
public void deleteCartByCustomer(Customer c) {
	// TODO Auto-generated method stub
	cartRepository.deleteCartByCustomer(c);
}


}

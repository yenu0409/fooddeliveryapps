package com.project.entity;

import java.util.List;

public class DishesPaging {

	private List<Dishes> dishes;
	private long totalDishes;
	
	public List<Dishes> getDishes() {
		return dishes;
	}
	public void setDishes(List<Dishes> dishes) {
		this.dishes = dishes;
	}
	
	public long getTotalDishes() {
		return totalDishes;
	}
	public void setTotalDishes(long totalDishes) {
		this.totalDishes = totalDishes;
	} 
}

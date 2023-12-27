package com.project.service;

import java.util.List;

import com.project.entity.Category;
import com.project.entity.Dishes;
import com.project.entity.DishesPaging;

public interface DishesServiceif {
	Dishes addDishes(Dishes dishes);
    List<Dishes> getAllDishess();
	Dishes getDishesByDishesId(long dishId);
	Dishes updateDishes(Dishes dishes, long dishId);
	void deleteDishes(long dishId);
	List<Dishes> findByCategory(Category category);
	DishesPaging findByCategory(Category category, Integer pageNo, Integer pageSize);
	DishesPaging getAllDishess(Integer pageNo, Integer pageSize);
}

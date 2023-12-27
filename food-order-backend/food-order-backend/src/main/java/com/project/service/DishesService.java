package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.project.entity.Category;
import com.project.entity.Dishes;
import com.project.entity.DishesPaging;
import com.project.exception.ResourceNotFound;
import com.project.repository.DishesRepository;

@Service
public class DishesService implements DishesServiceif {
	@Autowired
	private DishesRepository dishesRepository;

	@Override
	public Dishes addDishes(Dishes dishes) {
		System.out.println("Dish added Succesfully " + dishes);
		dishes.setDishname(dishes.getDishname());
		dishes.setDescription(dishes.getDescription());
		dishes.setMrpPrice(dishes.getMrpPrice());
		return dishesRepository.save(dishes);
	}

	@Override
	public Dishes updateDishes(Dishes dishes, long dishId) {

		Dishes existingDishes = dishesRepository.findById(dishId)
				.orElseThrow(() -> new ResourceNotFound("Dishes", "dishId", dishId));
		existingDishes.setDishname(dishes.getDishname());
		existingDishes.setMrpPrice(dishes.getMrpPrice());
		existingDishes.setImage(dishes.getImage());
		existingDishes.setDescription(dishes.getDescription());
		existingDishes.setCategory(dishes.getCategory());
		dishesRepository.save(existingDishes);
		return existingDishes;

	}
	
	@Override
	public void deleteDishes(long dishId) {
		dishesRepository.findById(dishId).orElseThrow(()->new ResourceNotFound("dishes","Id",dishId));
		dishesRepository.deleteById(dishId);	
	
	}
	
	@Override
	public List<Dishes> getAllDishess() {
		// TODO Auto-generated method stub
		return dishesRepository.findAll();
	}
	
	@Override
	public Dishes getDishesByDishesId(long dishId) {
		// TODO Auto-generated method stub
		return dishesRepository.findById(dishId).orElseThrow(()->new ResourceNotFound("Dishes","Id",dishId));
	}
	
	@Override
	public List<Dishes> findByCategory(Category category) {
		// TODO Auto-generated method stub
		return dishesRepository.findByCategory(category);
	}
	
	@Override
	public DishesPaging findByCategory(Category catgory, Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Dishes> pageResult = dishesRepository.findByCategory(catgory, paging);
		DishesPaging pr = new DishesPaging();
		pr.setTotalDishes(pageResult.getTotalElements());
		if(pageResult.hasContent()) {
            pr.setDishes(pageResult.getContent());
        } else {
        	 pr.setDishes(new ArrayList<Dishes>());
        }
		return pr;
	}
	
	@Override
	public DishesPaging getAllDishess(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Dishes> pageResult = dishesRepository.findAll(paging);
		DishesPaging pr = new DishesPaging();
		pr.setTotalDishes(pageResult.getTotalElements());
		System.out.println(">>>>>"+ pageResult.getTotalPages());
		if(pageResult.hasContent()) {
            pr.setDishes(pageResult.getContent());
        } else {
        	 pr.setDishes(new ArrayList<Dishes>());
        }
		return pr;
	}
}

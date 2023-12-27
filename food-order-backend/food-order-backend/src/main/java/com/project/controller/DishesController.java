package com.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Category;
import com.project.entity.Dishes;
import com.project.entity.DishesPaging;
import com.project.repository.DishesRepository;
import com.project.service.DishesService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/dishes")
public class DishesController {
	@Autowired
	private DishesService dishesService;
	
	@Autowired
	private DishesRepository dishesRepository;
	

	
	//to adddishes to cart
			@PostMapping("/adddishes")
			public ResponseEntity<Dishes> addDishes(@Valid @RequestBody Dishes dishes) {

				return new ResponseEntity<Dishes>(dishesService.addDishes(dishes), HttpStatus.CREATED);
			}
			
			
			// to get dishes by cart id
			@GetMapping("/dishes/{dishId}")
			public ResponseEntity<Dishes> getDishesById(@PathVariable("dishId") long dishId) {
				return new ResponseEntity<Dishes>(dishesService.getDishesByDishesId(dishId), HttpStatus.OK);
			}
			
			
			// to update dishes
			@PutMapping("/{dishId}")
			public ResponseEntity<Dishes> updateDishes(@Valid @PathVariable("dishId") long dishId, @RequestBody Dishes dishes) {
				return new ResponseEntity<Dishes>(dishesService.updateDishes(dishes, dishId), HttpStatus.OK);
			}
			
			//to delete dish
			@DeleteMapping("/{dishId}")
			public ResponseEntity<Boolean> deleteDishes(@PathVariable("dishId") long dishId) {
				dishesService.deleteDishes(dishId);
				boolean flag = true;
				return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
			}
			
			
			@GetMapping("/{categoryId}")
			public List<Dishes> getAllDishesByCategory(@PathVariable("categoryId") int categoryId) {
				Category c = Category.valueOf(categoryId);
				return dishesService.findByCategory(c);
			}
			
			@GetMapping("/{categoryId}/{pageNo}/{pageSize}")
			public DishesPaging getAllDishesByCategory(@PathVariable("categoryId") int categoryId, @PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) 
			{
				Category c = Category.valueOf(categoryId);
				return dishesService.findByCategory(c, pageNo, pageSize);
			}
			
			@GetMapping("/{pageNo}/{pageSize}")
			public DishesPaging getAllDishes(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize) {
				return dishesService.getAllDishess(pageNo, pageSize);
			}

			@GetMapping("/disheslist")
				public List<Dishes> getAllDishess() {
					// TODO Auto-generated method stub
					return dishesRepository.findAll();
				}
}



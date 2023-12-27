package com.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Category;
import com.project.entity.Dishes;

	@Repository
	public interface DishesRepository  extends JpaRepository<Dishes, Long>, PagingAndSortingRepository<Dishes, Long> {
		public List<Dishes> findByDishId(long dishId);
		public List<Dishes> findByCategory(Category category);
		public Page<Dishes> findByCategory(Category category, Pageable page);
}

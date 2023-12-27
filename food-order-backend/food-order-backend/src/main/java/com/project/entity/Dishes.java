package com.project.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
@Entity
@Table(name="Dish_table")
@SequenceGenerator(name = "generator2", sequenceName = "gen2", initialValue = 5000)
public class Dishes {
			@Id
			@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator2")
			@Column(name="dish_id")
			private long dishId;
			
			@NotEmpty(message = "dish name is required.")
		    @Column(name = "dish", nullable = false, length = 20)
			private String dishname;

			@Column(name="dish_image")
			@NotEmpty
			private String image;
			
			@NotEmpty(message = "Dish description is required.")
			@Column(name = "description", nullable = false)
			private String description;
			
			@Column(name = "mrp_price", nullable = false, precision = 10, scale = 2)
		    private double mrpPrice;
			private Category category;
			
			
			

			public long getDishId() {
				return dishId;
			}

			public void setDishId(long dishId) {
				this.dishId = dishId;
			}

			public String getDishname() {
				return dishname;
			}

			public void setDishname(String dishname) {
				this.dishname = dishname;
			}

			public String getImage() {
				return image;
			}

			public void setImage(String image) {
				this.image = image;
			}

			public double getMrpPrice() {
				return mrpPrice;
			}

			public void setMrpPrice(double mrpPrice) {
				this.mrpPrice = mrpPrice;
			}



			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public Category getCategory() {
				return category;
			}

			public void setCategory(Category category) {
				this.category = category;
			}

			
			public Dishes() {
				super();
				// TODO Auto-generated constructor stub
			}

			@Override
			public String toString() {
				return "Dishes [dishId=" + dishId + ", dishname=" + dishname + ", image=" + image + ", description="
						+ description + ", mrpPrice=" + mrpPrice + ", category=" + category + "]";
			}

			

	}

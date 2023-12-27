package com.project.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


	@Entity
	@Table(name = "order_table")
	@SequenceGenerator(name = "generator6", sequenceName = "gen", initialValue = 1000)

	public class Order {
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator6")
		@Column(name = "order_id")
		private long  orderId;
		
		@Column(name = "mrp_price")
	    private double mrpPrice;
		
		@Column(name = "quantity")
		private long quantity;
		  
		  @Column(name = "total_price")
		    private double totalPrice;
		  
		  @Column(name = "order_status")
		    private String orderStatus;
		  
		  @Column(name = "payment_status")
		    private String paymentStatus;
		
		@Column(name="ordered_date")
		private Date orderedDate;
		
		@NotEmpty(message = "Dish name is required.")
	    @Column(name = "dishname", nullable = false, length = 20)
		private String dishname;

		@Column(name="dish_image")
		@NotEmpty
		private String image;
		
		@ManyToOne( cascade=CascadeType.MERGE)
		@JoinColumn(name="customer_id")
	    private Customer customer;
		
		public long getOrderId() {
			return orderId;
		}

		public Order() {
			super();
			
		}

		public Order(long orderId, double mrpPrice, long quantity, double totalPrice, String orderStatus,
				String paymentStatus, Date orderedDate,
				@NotEmpty(message = "Dish name is required.") String dishname, @NotEmpty String image,
				Customer customer) {
			super();
			this.orderId = orderId;
			this.mrpPrice = mrpPrice;
			this.quantity = quantity;
			this.totalPrice = totalPrice;
			this.orderStatus = orderStatus;
			this.paymentStatus = paymentStatus;
			this.orderedDate = orderedDate;
			this.dishname = dishname;
			this.image = image;
			this.customer = customer;
		}

		@Override
		public String toString() {
			return "Order [orderId=" + orderId + ", mrpPrice=" + mrpPrice + ", quantity=" + quantity + ", totalPrice="
					+ totalPrice + ", orderStatus=" + orderStatus + ", paymentStatus=" + paymentStatus
					+ ", orderedDate=" + orderedDate + ", dishname=" + dishname + ", image=" + image
					+ ", customer=" + customer + "]";
		}

		public void setOrderId(long orderId) {
			this.orderId = orderId;
		}

		public double getMrpPrice() {
			return mrpPrice;
		}

		public void setMrpPrice(double mrpPrice) {
			this.mrpPrice = mrpPrice;
		}

		public long getQuantity() {
			return quantity;
		}

		public void setQuantity(long quantity) {
			this.quantity = quantity;
		}

		public double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(double totalPrice) {
			this.totalPrice = totalPrice;
		}

		public String getOrderStatus() {
			return orderStatus;
		}

		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}

		public String getPaymentStatus() {
			return paymentStatus;
		}

		public void setPaymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
		}

		public Date getOrderedDate() {
			return orderedDate;
		}

		public void setOrderedDate(Date orderedDate) {
			this.orderedDate = orderedDate;
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

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		
		


}


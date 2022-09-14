package com.example.order.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "order_number")
	private String orderNumber;
	
	@Column(name = "dessert_name")
	private String dessertName;
	
	@Column(name = "cook_name")
	private String cookName;
	
	@Column(name = "order_quantity")
	private long orderQuantity;
	
	@Column(name = "order_price")
	private long orderPrice;
	
	public Order() {
		
	}
	
	public Order(String orderNumber, String dessertName, String cookName, long orderQuantity, long orderPrice) {
		super();
		this.orderNumber = orderNumber;
		this.dessertName = dessertName;
		this.cookName = cookName;
		this.orderQuantity = orderQuantity;
		this.orderPrice = orderPrice;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getDessertName() {
		return dessertName;
	}
	public void setDessertName(String dessertName) {
		this.dessertName = dessertName;
	}
	public String getCookName() {
		return cookName;
	}
	public void setCookName(String cookName) {
		this.cookName = cookName;
	}
	public long getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(long orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public long getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(long orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	

}

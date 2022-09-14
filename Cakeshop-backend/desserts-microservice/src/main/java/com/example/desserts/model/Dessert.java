package com.example.desserts.model;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "desserts")
public class Dessert {
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long id;
	 
	 @Column(name = "dessert_number")
	 private String dessertNumber;
	 
	 @Column(name = "dessert_name")
	 private String dessertName;
	 
	 @Column(name = "dessert_type")
	 private String dessertType;
	
	public Dessert() {
		
	}
	
	public Dessert(String dessertNumber, String dessertName, String dessertType) {
		super();
		this.dessertNumber = dessertNumber;
		this.dessertName = dessertName;
		this.dessertType = dessertType;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDessertNumber() {
		return dessertNumber;
	}
	public void setDessertNumber(String dessertNumber) {
		this.dessertNumber = dessertNumber;
	}
	public String getDessertName() {
		return dessertName;
	}
	public void setDessertName(String dessertName) {
		this.dessertName = dessertName;
	}
	public String getDessertType() {
		return dessertType;
	}
	public void setDessertType(String dessertType) {
		this.dessertType = dessertType;
	}

	
	
	
	
}

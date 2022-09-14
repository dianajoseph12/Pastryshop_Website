package com.example.cook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cooks")
public class Cook {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "cook_number")
	private String cookNumber;
	
	@Column(name = "cook_name")
	private String cookName;
	
	@Column(name = "cook_type")
	private String cookType;
	
	public Cook () {
		
	}
	
	public Cook(String cookNumber, String cookName, String cookType) {
		super();
		this.cookNumber = cookNumber;
		this.cookName = cookName;
		this.cookType = cookType;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCookNumber() {
		return cookNumber;
	}
	public void setCookNumber(String cookNumber) {
		this.cookNumber = cookNumber;
	}
	public String getCookName() {
		return cookName;
	}
	public void setCookName(String cookName) {
		this.cookName = cookName;
	}
	public String getCookType() {
		return cookType;
	}
	public void setCookType(String cookType) {
		this.cookType = cookType;
	}
	
	
}

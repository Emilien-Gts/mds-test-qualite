package com.tactfactory.monprojetsb.entities;

import javax.persistence.*;

public class Product {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private Float price;
	
	@ManyToOne
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product(Long id, String name, Float price, User user) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.user = user;
	}
}

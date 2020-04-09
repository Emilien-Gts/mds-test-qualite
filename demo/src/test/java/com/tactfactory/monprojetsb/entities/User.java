package com.tactfactory.monprojetsb.entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String firstname;
	private String lastname;
	
	@OneToMany
	private List<Product> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public User(Long id, String firstname, String lastname, List<Product> products) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.products = products;
	}
}

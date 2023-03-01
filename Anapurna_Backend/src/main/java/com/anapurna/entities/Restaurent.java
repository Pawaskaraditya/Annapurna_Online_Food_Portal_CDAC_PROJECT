package com.anapurna.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Restaurent")
public class Restaurent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	
	@Column(name="Address")
	private String address_text;
	
	@Column(name="pin_code")
	private int pinCode;
	
	@OneToOne(mappedBy = "restaurentId")
	private RestaurentManager restaurentManager;
	
	@OneToMany(mappedBy = "restaurentId")
	private List<Orders> orders;
	
	@OneToMany(mappedBy = "restaurentId")
	private List<FoodItem> foodItems;

	public Restaurent() {
		super();
		
	}

	public Restaurent(int id, String name, String address_text, int pinCode) {
		super();
		this.id = id;
		this.name = name;
		this.address_text = address_text;
		this.pinCode = pinCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress_text() {
		return address_text;
	}

	public void setAddress_text(String address_text) {
		this.address_text = address_text;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public RestaurentManager getRestaurentManager() {
		return restaurentManager;
	}

	public void setRestaurentManager(RestaurentManager restaurentManager) {
		this.restaurentManager = restaurentManager;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public List<FoodItem> getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(List<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}

	@Override
	public String toString() {
		return "Restaurent [id=" + id + ", name=" + name + ", address_text=" + address_text + ", pinCode=" + pinCode
				+ "]";
	}
	
	
	
	
	
	

}

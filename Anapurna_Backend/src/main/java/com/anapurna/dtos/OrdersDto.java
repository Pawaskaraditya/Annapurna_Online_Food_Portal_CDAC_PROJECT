package com.anapurna.dtos;

import java.util.List;

//This class will map with the Orders entity.
//Will consist the order details of food item, along with customer and restaurent details and delivery person

public class OrdersDto {
	
	private int orderId;
	
	private List<FoodItemInOrderDto> fooditems;
	
	private CustomerDto customer;
	
	private RestaurentHomePageDto restaurent;
	
	private String status;
	
	private DeliveryPersonDto deliveryPerson;

	public OrdersDto() {
		super();
		
	}

	public OrdersDto(int orderId, List<FoodItemInOrderDto> fooditems, CustomerDto customer,
			RestaurentHomePageDto restaurent, String status) {
		super();
		this.orderId = orderId;
		this.fooditems = fooditems;
		this.customer = customer;
		this.restaurent = restaurent;
		this.status = status;
	}

	public OrdersDto(int orderId, List<FoodItemInOrderDto> fooditems, CustomerDto customer,
			RestaurentHomePageDto restaurent, String status, DeliveryPersonDto deliveryPerson) {
		super();
		this.orderId = orderId;
		this.fooditems = fooditems;
		this.customer = customer;
		this.restaurent = restaurent;
		this.status = status;
		this.deliveryPerson = deliveryPerson;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public List<FoodItemInOrderDto> getFooditems() {
		return fooditems;
	}

	public void setFooditems(List<FoodItemInOrderDto> fooditems) {
		this.fooditems = fooditems;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public RestaurentHomePageDto getRestaurent() {
		return restaurent;
	}

	public void setRestaurent(RestaurentHomePageDto restaurent) {
		this.restaurent = restaurent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DeliveryPersonDto getDeliveryPerson() {
		return deliveryPerson;
	}

	public void setDeliveryPerson(DeliveryPersonDto deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}
	
	
	
	
	

}

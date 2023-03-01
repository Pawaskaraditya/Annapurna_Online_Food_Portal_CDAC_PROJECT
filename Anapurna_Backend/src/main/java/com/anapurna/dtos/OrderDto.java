package com.anapurna.dtos;

import java.util.List;

//This class will give all the order ids list. will be beneficial for managers.
public class OrderDto {
	
	List<Integer> orderIds;

	public OrderDto() {
		super();
		
	}

	public OrderDto(List<Integer> orderIds) {
		super();
		this.orderIds = orderIds;
	}

	public List<Integer> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<Integer> orderIds) {
		this.orderIds = orderIds;
	}
	
	
	
	

}

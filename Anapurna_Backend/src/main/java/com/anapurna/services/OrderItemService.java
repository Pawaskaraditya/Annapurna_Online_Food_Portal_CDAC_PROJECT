package com.anapurna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anapurna.daos.*;
import com.anapurna.entities.*;

public class OrderItemService {
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	public List<OrderItem> findAllOrderItems() {
		return orderItemDao.findAll();
	}
}



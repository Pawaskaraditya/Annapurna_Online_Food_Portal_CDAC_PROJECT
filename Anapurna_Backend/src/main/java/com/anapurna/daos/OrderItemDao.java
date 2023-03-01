package com.anapurna.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anapurna.entities.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {

}

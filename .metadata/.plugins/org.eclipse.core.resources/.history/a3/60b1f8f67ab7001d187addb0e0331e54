package com.anapurna.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anapurna.entities.DeliveryPerson;
import com.anapurna.entities.Orders;
import com.anapurna.entities.Restaurent;

public interface OrdersDao extends JpaRepository<Orders, Integer> {
	
	List<Orders> findByRestaurentIdAndStatus(Restaurent restaurentId,String status);
	
	List<Orders> findByStatus(String status);
	
	List<Orders> findByRestaurentId(Restaurent restaurentId);
	
	List<Orders> findByAssignToDeliveryPersonIdAndStatus(DeliveryPerson deliveryPerson,String status);

}

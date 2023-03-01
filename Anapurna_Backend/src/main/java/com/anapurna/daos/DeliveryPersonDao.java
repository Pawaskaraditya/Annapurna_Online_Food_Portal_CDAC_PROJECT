package com.anapurna.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anapurna.entities.DeliveryPerson;

public interface DeliveryPersonDao extends JpaRepository<DeliveryPerson, Integer> {
	
	DeliveryPerson findByEmail(String email);
	
	List<DeliveryPerson> findByIsAvailable(boolean status);

}

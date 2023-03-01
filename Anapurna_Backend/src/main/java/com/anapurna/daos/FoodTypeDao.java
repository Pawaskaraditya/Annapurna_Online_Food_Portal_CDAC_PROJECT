package com.anapurna.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anapurna.entities.FoodType;

public interface FoodTypeDao extends JpaRepository<FoodType, Integer> {
	
	

}

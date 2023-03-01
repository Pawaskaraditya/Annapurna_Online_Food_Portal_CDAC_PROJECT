package com.anapurna.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anapurna.entities.FoodItem;
import com.anapurna.entities.Restaurent;

public interface FoodItemDao extends JpaRepository<FoodItem, Integer>{
	
	List<FoodItem> findFoodItemsByRestaurentId(Restaurent restaurentId);

}

package com.anapurna.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anapurna.daos.FoodTypeDao;
import com.anapurna.dtos.DaoToEntityConverter;
import com.anapurna.dtos.FoodTypeDto;
import com.anapurna.entities.FoodType;

@Service
@Transactional


//Class to get all the food types dto with 
//FoodType returns id and its name.
public class FoodTypeService {
	
	@Autowired
	private FoodTypeDao foodTypeDao;
	
	public List<FoodTypeDto> findAllFoodTypes(){
		
		List<FoodType> foodTypeList=foodTypeDao.findAll();
		List<FoodTypeDto> foodTypeDtoList=new ArrayList<FoodTypeDto>();
		
		foodTypeList.forEach(foodType->foodTypeDtoList.add(DaoToEntityConverter.FoodTypeToFoodTypeDto(foodType)));
		return foodTypeDtoList;
		
	}
	
	

}

package com.anapurna.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anapurna.daos.FoodItemDao;
import com.anapurna.daos.FoodTypeDao;
import com.anapurna.daos.RestaurentDao;
import com.anapurna.dtos.DaoToEntityConverter;
import com.anapurna.dtos.FoodItemHomePageDto;
import com.anapurna.entities.FoodItem;
import com.anapurna.entities.FoodType;
import com.anapurna.entities.Restaurent;
import com.anapurna.daos.RestaurentDao;
import com.anapurna.entities.Restaurent;

@Service
@Transactional
public class FoodItemService {
	
	@Autowired
	private RestaurentDao restaurantDao;
	
	@Autowired
	private FoodItemDao foodItemDao;
	
	@Autowired
	private FoodTypeDao foodTypeDao;
	
	public List<FoodItem> findAllFoodItems(){
		return foodItemDao.findAll();
	}
	
	
	//Returns foodItemsDto list(ie food items list) from a particular restaurent.
	public List<FoodItemHomePageDto> findAllFoodItemsFromRestaurent(int restaurentId){
		
		Restaurent restId=null;
		
		try {
			restId=restaurantDao.findById(restaurentId).get();
		}catch (Exception e) {
			return null;
		}
		
		List<FoodItem> foodItems=foodItemDao.findFoodItemsByRestaurentId(restId);
		List<FoodItemHomePageDto> foodItemsDtos=new ArrayList<FoodItemHomePageDto>();
		foodItems.forEach(foodItem->foodItemsDtos.add(DaoToEntityConverter.foodItemEntityToFoodItemHomePageDto(foodItem)));
		
		return foodItemsDtos;
	}
	
	
	//This will deliver list of Food Item dto by the list of ids.
	public List<FoodItemHomePageDto> findAllFoodItemsByIds(List<Integer> listOfFoodItems) {
		List<FoodItemHomePageDto> foodItemsDtos = new ArrayList<FoodItemHomePageDto>();
		FoodItem foodItem = null;
		
		for (Integer foodItemId : listOfFoodItems) {
			
			try {
				foodItem = foodItemDao.getById(foodItemId);
				foodItemsDtos.add(DaoToEntityConverter.foodItemEntityToFoodItemHomePageDto(foodItem));
			} catch (Exception e) {
				continue;
			}
		}
		return foodItemsDtos;
	}
	
	
	//Saving the particular food item 
	public boolean saveFoodItem(FoodItem food) {
		FoodItem foodItem = foodItemDao.save(food);
		System.out.println(foodItem.getId());
		return true;
	}
	
	
	
	//This will take foodItem dto with all the details of the food item
	//And this furthers the food item.
	public boolean saveFoodItemDto(FoodItemHomePageDto foodItemHomePageDto) {
		try {
			FoodItem foodItem = new FoodItem();
			
			FoodType foodtype = foodTypeDao.getById(foodItemHomePageDto.getFoodTypeId());
			Restaurent restaurant = restaurantDao.getById(foodItemHomePageDto.getRestaurantId());
			
			foodItem.setFoodTypeId(foodtype);
			foodItem.setRestaurentId(restaurant);
			foodItem.setName(foodItemHomePageDto.getName());
			foodItem.setPrice(foodItemHomePageDto.getPrice());
			foodItem.setVegetarian(foodItemHomePageDto.isVegetarian());
			foodItem.setImagePath(foodItemHomePageDto.getImagePath());
			
			foodItemDao.save(foodItem);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}
	
	
	
    //This will get the particular food item dto by taking food item id
	
	public FoodItemHomePageDto getDtoById(int foodItemId) {
		
		FoodItem fooditem = foodItemDao.getById(foodItemId);
		FoodItemHomePageDto foodItemHomePageDto = DaoToEntityConverter.foodItemAddEntityToDto(fooditem);
		return foodItemHomePageDto;
		
	}
	
	
	
	
	//This will be used to update a particular food item by taking the details from food item dto
	public boolean updateFoodItem(FoodItemHomePageDto foodItemHomePageDto) {
		try {
			FoodItem foodItem = foodItemDao.getById(foodItemHomePageDto.getId());
			foodItem.setName(foodItemHomePageDto.getName());
			foodItem.setFoodTypeId(foodTypeDao.getById(foodItemHomePageDto.getFoodTypeId()));
			foodItem.setPrice(foodItemHomePageDto.getPrice());
			foodItem.setImagePath(foodItemHomePageDto.getImagePath());
			foodItem.setVegetarian(foodItemHomePageDto.isVegetarian());
			
			foodItemDao.save(foodItem);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	
	
	

}

package com.anapurna.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anapurna.daos.DeliveryPersonDao;
import com.anapurna.daos.RestaurentManagerDao;
import com.anapurna.dtos.AnapurnaResponse;
import com.anapurna.dtos.Credentials;
import com.anapurna.dtos.DaoToEntityConverter;
import com.anapurna.dtos.DeliveryPersonDto;
import com.anapurna.dtos.FoodItemHomePageDto;
import com.anapurna.dtos.FoodTypeDto;
import com.anapurna.dtos.OrdersDto;
import com.anapurna.dtos.RestManAndRestSignUpDto;
import com.anapurna.dtos.RestaurentManagerDto;
import com.anapurna.entities.Orders;
import com.anapurna.entities.RestaurentManager;
import com.anapurna.services.DeliveryPersonService;
import com.anapurna.services.FoodItemService;
import com.anapurna.services.FoodTypeService;
import com.anapurna.services.OrdersService;
import com.anapurna.services.RestaurantManagerService;
import com.anapurna.services.RestaurentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class RestaurantManagerController {
	
	
	@Autowired
	private RestaurantManagerService restaurantManagerService;
	
	@Autowired
	private RestaurentService restaurantService;
	
	
	@Autowired
	private DeliveryPersonService deliveryPersonService;
	
	@Autowired
	private OrdersService ordersService;
	
	
	@Autowired
	private FoodTypeService foodTypeService;
	
	@Autowired
	private FoodItemService foodItemService;
	
	
	@GetMapping("/restaurantmanager/signin")
	public ResponseEntity<AnapurnaResponse> signIn(@RequestBody Credentials cred){
		RestaurentManagerDto restaurentManagerDto=restaurantManagerService.findRestaurentManagerByEmailAndPassword(cred);
		if(restaurentManagerDto==null) {
			return AnapurnaResponse.error("Not found");
		}
		
		return AnapurnaResponse.success(restaurentManagerDto);
	}
	
	
	@PostMapping("restaurantmanager/signUp")
	public ResponseEntity<AnapurnaResponse> restManagerAndRestSignUp(@RequestBody RestManAndRestSignUpDto dto){
		boolean status=restaurantService.restManagerAndRestSignUp(dto);
		
		if(status) {
			return AnapurnaResponse.success("Added Restaurant and Restaurant Manager");
		}
		
		return AnapurnaResponse.error("Could not find Restaurant and Restaurant Manager");
	}
	
	
	@GetMapping("/restaurantmanager/{id}")
	public ResponseEntity<AnapurnaResponse> getRestaurantManagerById(@PathVariable("id") int id){
		Optional<RestaurentManager> r=restaurantManagerService.getRestaurentManagerById(id);
	    if(r==null) {
	    	return AnapurnaResponse.error("Not found");
	}
	    
	    return AnapurnaResponse.success(r);
	}
	
	
	
	@GetMapping("restaurantmanager/availabledeliveryperson/{status}")
	public ResponseEntity<AnapurnaResponse> getDeliveryPersonByAvailable(@PathVariable("status") boolean status){
		List<DeliveryPersonDto> dto=deliveryPersonService.findDeliveryPersonByIsAvailable(status);
		
		 if(dto==null) {
			 return AnapurnaResponse.error("Not Available");
		 }
		 
		 return AnapurnaResponse.success(dto);
	}
	
	
	@PostMapping("/restaurantmanager/allorders/{restaurantId}")
	public ResponseEntity<AnapurnaResponse> getAllOrdersByRestaurant(@PathVariable("restaurantId") int restaurantId){
		
		List<Orders> orders=ordersService.findAllOrdersByRestaurantid(restaurantId);
		if(orders==null || orders.isEmpty()) {
			return AnapurnaResponse.error("list Empty!");
		}
		
		List<OrdersDto> oredersDtoList=DaoToEntityConverter.ordersToOrdersDto(orders);
		return AnapurnaResponse.success(oredersDtoList);
		
	}
	
	
	@PostMapping("/restaurantmanager/arrivedorders/{restaurantId}")
	public ResponseEntity<AnapurnaResponse> getArrivedOrders(@PathVariable("restaurantId") int restaurantId){
		String status="arrived";
		
		List<Orders> orders=ordersService.findArrivedOrdersByRestaurantIdAndStatus(restaurantId, status);
		if(orders==null || orders.isEmpty()) {
			return AnapurnaResponse.error("List Empty!");
		}
		
		List<OrdersDto> ordersdtoList=DaoToEntityConverter.ordersToOrdersDto(orders);
		
		return AnapurnaResponse.success(ordersdtoList);
	}
	
	
	@GetMapping("/foodtypes")
	public ResponseEntity<AnapurnaResponse> getAllFoodTypes(){
		List<FoodTypeDto> foodTypes=foodTypeService.findAllFoodTypes();
		return AnapurnaResponse.success(foodTypes);
		
	}
	
	
	@PostMapping("/restaurantmanager/addfooditem")
	public ResponseEntity<AnapurnaResponse> addFoodItem(@RequestBody FoodItemHomePageDto foodItemHomePageDto ){
		
		boolean status=foodItemService.saveFoodItemDto(foodItemHomePageDto);
		if(!status) {
			return AnapurnaResponse.error("Couldn't add food item");
		}
		
		return AnapurnaResponse.success("Food item added");
	}
	
	
	@PostMapping("/foodTypes/edit/{foodItemId}")
	public ResponseEntity<AnapurnaResponse> updateFoodItemDetails(@RequestBody FoodItemHomePageDto foodItemHomePageDto){
		boolean status=foodItemService.updateFoodItem(foodItemHomePageDto);
		if(!status) {
			return AnapurnaResponse.error("Couldn't update food item");
			
		}
		
		
		return AnapurnaResponse.success("Food Item Update");
	}
	
	
	@PostMapping("/orders/assign/{orderId}/{deliveryPersonId}")
	public ResponseEntity<AnapurnaResponse> assignDeliveryPersonToOrder(@PathVariable("orderId") int orderId,
			@PathVariable("deliveryPersonId") int deliveryPersonId) {
		
		boolean status=ordersService.assignDeliveryPersonToOrder(orderId, deliveryPersonId);
		if(status==false) {
			AnapurnaResponse.error("Order not assigned");
		}
		
		return AnapurnaResponse.success("Order assigned successfully");
		
	}
	
	
	@GetMapping("/foodTypes/edit/{foodItemId}")
	public ResponseEntity<AnapurnaResponse> getfoodItemDetails(@PathVariable("foodItemId") int foodItemId){
		FoodItemHomePageDto foodItemHomePageDto=foodItemService.getDtoById(foodItemId);
		
		List<FoodTypeDto> foodTypes=foodTypeService.findAllFoodTypes();
		List<Object> resultData=new ArrayList<Object>();
		
		resultData.add(foodItemHomePageDto);
		resultData.add(foodTypes);
		
		return AnapurnaResponse.success(resultData);
	}
	
	
	@GetMapping("/fooditem/restaurant/{restaurantId}")
	public ResponseEntity<AnapurnaResponse> getAllFoodItemsByRestaurantId(@PathVariable("restaurantId") int restaurantId){
		List<FoodItemHomePageDto> foodItemDtos=foodItemService.findAllFoodItemsFromRestaurent(restaurantId);
		
		if(foodItemDtos==null || foodItemDtos.isEmpty()) {
			return AnapurnaResponse.error("No food items found, please add food items.");
		}
		
		return AnapurnaResponse.success(foodItemDtos);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

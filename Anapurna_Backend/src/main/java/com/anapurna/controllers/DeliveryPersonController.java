package com.anapurna.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anapurna.dtos.AnapurnaResponse;
import com.anapurna.dtos.Credentials;
import com.anapurna.dtos.DeliveryPersonDto;
import com.anapurna.dtos.DeliveryPersonHomePageDto;
import com.anapurna.dtos.DeliveryPersonSignUpDto;
import com.anapurna.services.DeliveryPersonService;
import com.anapurna.services.OrdersService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class DeliveryPersonController {
	
	@Autowired
	private DeliveryPersonService deliveryPersonService;
	
	@Autowired
	private OrdersService ordersService;
	
	
	@PostMapping("/deliveryperson/signup")
	public ResponseEntity<AnapurnaResponse> deliveryPersonSignUp(@RequestBody DeliveryPersonSignUpDto deliveryPersonSignUpDto){
		boolean status=deliveryPersonService.addDeliveryPerson(deliveryPersonSignUpDto);
		
		if(status) {
			return AnapurnaResponse.success("Delivery Person added");
		}
		
		return AnapurnaResponse.error("Delivery person could not be added");
	}
	
	
	@GetMapping("/deliveryperson/signin")
	public ResponseEntity<AnapurnaResponse> signIn(@RequestBody Credentials cred){
		DeliveryPersonDto deliveryPersonDto=deliveryPersonService.findDeliveryPersonByEmailAndPassword(cred);
		if(deliveryPersonDto==null) {
			return AnapurnaResponse.error("Couldn't find Delivery Person with that credentials");
		}
		
		return AnapurnaResponse.success(deliveryPersonDto);
	}
	
	
	@GetMapping("/deliverypersonhomepage/{id}")
	public ResponseEntity<AnapurnaResponse> findDeliveryPersonHomePageDetails(@PathVariable("id") int id){
		DeliveryPersonHomePageDto deliveryPersonDto=ordersService.getdeliveryPersonHomePageDtoById(id);
		if(deliveryPersonDto==null) {
			return AnapurnaResponse.error("Couldn't find Delivery Person Details with that id");
		}
		
		return AnapurnaResponse.success(deliveryPersonDto);
	}
	
	
	@PostMapping("/deliveryperson/{orderId}/{status}")
	public ResponseEntity<AnapurnaResponse> setStatusByOrder(@PathVariable ("orderId") int orderId, @PathVariable("status") String status){
		boolean updateStatus=ordersService.setStatusForOrder(orderId, status);
		
		if(!updateStatus) {
			return AnapurnaResponse.error("Couldn't update status for order");
		}
		
		return AnapurnaResponse.success("Order status updated");
	}
	
	
	
	@GetMapping("/orders/deliveryperson/{id}")
	public ResponseEntity<AnapurnaResponse> getAllOrdersByDeliveryPersonId(@PathVariable("id") int deliveryPersonId){
		List<DeliveryPersonHomePageDto> dphpDtoList=ordersService.findAllOrdersByDeliveryPerson(deliveryPersonId);
		if(dphpDtoList==null || dphpDtoList.isEmpty()) {
			AnapurnaResponse.error("List empty!");
		}
		
		return AnapurnaResponse.success(dphpDtoList);
	}
	
	
	
	@PostMapping("/deliveryperson/arrivedorders/{deliverypersonId}")
	public ResponseEntity<AnapurnaResponse> getArrivedOrders(@PathVariable("deliverypersonId") int deliverypersonId){
		String status="arrived";
		List<DeliveryPersonHomePageDto> orders=ordersService.findArrivedordersByDeliverypersonIdAndStatus(deliverypersonId, status);
		if(orders==null || orders.isEmpty()) {
			return AnapurnaResponse.error("No orders assigned");
		}
		
		return AnapurnaResponse.success(orders);
	}
	
	
	@GetMapping("/deliveryperson/{deliverypersonId}/status/{status}")
	public ResponseEntity<AnapurnaResponse> getOrders(@PathVariable("deliverypersonId") int deliverypersonId, @PathVariable("status") String status){
		List<DeliveryPersonHomePageDto> orders=ordersService.findArrivedordersByDeliverypersonIdAndStatus(deliverypersonId, status);
		if(orders==null || orders.isEmpty()) {
			return AnapurnaResponse.error("No Orders assigned");
		}
		
		return AnapurnaResponse.success(orders);
	}
	
	
	

}

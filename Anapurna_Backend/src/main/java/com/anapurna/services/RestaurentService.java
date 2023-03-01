package com.anapurna.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anapurna.daos.RestaurentDao;
import com.anapurna.daos.RestaurentManagerDao;
import com.anapurna.dtos.DaoToEntityConverter;
import com.anapurna.dtos.RestManAndRestSignUpDto;
import com.anapurna.dtos.RestaurentHomePageDto;
import com.anapurna.entities.Restaurent;
import com.anapurna.entities.RestaurentManager;

//Finds all Restaurents and also by its homepagedtos and used for registration of restaurent with its manager details.

@Service
@Transactional
public class RestaurentService {
	
	@Autowired 
	private RestaurentDao restaurentDao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private RestaurentManagerDao restaurentManagerDao;
	
	public List<Restaurent> findAllRestaurents(){
		return restaurentDao.findAll();
	}
	
	
	//returns List of restaurents in and return in dto format.
	public List<RestaurentHomePageDto> findAllRestaurentsHomePageDto(){
		List<Restaurent> restList=findAllRestaurents();
		
		List<RestaurentHomePageDto> restDtoList=new ArrayList<RestaurentHomePageDto>();
		restList.forEach(rest->restDtoList.add(DaoToEntityConverter.restaurentEntityToRestaurentHomePageDto(rest)));
		
		return restDtoList;
	}
	
	//Takes the manager and restaurent details from restManAndRestSignupDto for registering both.
	public boolean restManagerAndRestSignUp(RestManAndRestSignUpDto dto) {
		
		try {
			Restaurent rest=new Restaurent();
			rest.setName(dto.getRestaurantName());
			rest.setAddress_text(dto.getRestaurantAdressText());
			rest.setPinCode(dto.getRestaurantPinCode());
			
			rest=restaurentDao.save(rest);
			entityManager.refresh(rest);
			
			
			RestaurentManager restMan=new RestaurentManager();
			restMan.setName(dto.getManagerName());
			restMan.setEmail(dto.getManagerEmail());
			restMan.setPassword(dto.getManagerPassword());
			restMan.setRestaurentId(rest);
			
			restMan=restaurentManagerDao.save(restMan);
			entityManager.refresh(restMan);
		}catch (Exception e) {
			return false;
		}
		
		return true;
		
	}

}

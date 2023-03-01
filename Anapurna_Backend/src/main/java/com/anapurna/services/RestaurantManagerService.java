package com.anapurna.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anapurna.daos.RestaurentManagerDao;
import com.anapurna.dtos.Credentials;
import com.anapurna.dtos.DaoToEntityConverter;
import com.anapurna.dtos.RestaurentManagerDto;
import com.anapurna.entities.RestaurentManager;

@Service
@Transactional
public class RestaurantManagerService {
	
	@Autowired
	private RestaurentManagerDao restaurentManagerDao;
	
	public List<RestaurentManager> findAllRestaurentManager(){
		return restaurentManagerDao.findAll();
	}
	
	public Optional<RestaurentManager> getRestaurentManagerById(int Id){
		return restaurentManagerDao.findById(Id);
	}
	
	public boolean saveRestaurentManager(RestaurentManager rest) {
		restaurentManagerDao.save(rest);
		return true;
	}
	
	public RestaurentManagerDto getRestaurentManagerDtoById(int id) {
		Optional<RestaurentManager> rest=restaurentManagerDao.findById(id);
		
		RestaurentManager r=null;
		
		try {
			rest.get();
		}catch (Exception e) {
			r=null;
		    return null;
		}
		
		RestaurentManagerDto restDto=DaoToEntityConverter.RestaurentManagerEntityToDto(r);
		return restDto;
	}
	
	
	/*for sign in for restaurentManager.
	 * returns- RestaurentManagerdto
	 */
	
	public RestaurentManagerDto findRestaurentManagerByEmailAndPassword(Credentials cred) {
		RestaurentManager rest=restaurentManagerDao.findByEmail(cred.getEmail());
		
		if(rest==null || !rest.getPassword().equals(cred.getPassword())) {
			return null;
		}
		
		RestaurentManagerDto restManagerDto=DaoToEntityConverter.RestaurentManagerToRestaurentManagerDto(rest);
		return restManagerDto;
	}
	
	
	
	

}

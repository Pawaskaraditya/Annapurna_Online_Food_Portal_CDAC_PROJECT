package com.anapurna.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anapurna.entities.RestaurentManager;

public interface RestaurentManagerDao extends JpaRepository<RestaurentManager, Integer>{
	
	RestaurentManager findByEmail(String email);

}

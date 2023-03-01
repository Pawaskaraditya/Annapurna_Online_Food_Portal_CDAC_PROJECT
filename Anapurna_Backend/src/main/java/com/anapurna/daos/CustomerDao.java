package com.anapurna.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anapurna.entities.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer>{
	
	Customer findByEmail(String email);

}

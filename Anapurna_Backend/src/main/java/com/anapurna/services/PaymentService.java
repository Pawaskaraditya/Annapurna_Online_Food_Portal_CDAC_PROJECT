package com.anapurna.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anapurna.daos.PaymentDao;
import com.anapurna.entities.Payment;

@Service
@Transactional
public class PaymentService {
	
	@Autowired
	private PaymentDao paymentDao;
	
	public List<Payment> findAllPayments(){
		return paymentDao.findAll();
	}
	
	

}

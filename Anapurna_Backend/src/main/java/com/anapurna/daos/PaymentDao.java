package com.anapurna.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anapurna.entities.Payment;

public interface PaymentDao extends JpaRepository<Payment, Integer> {

}

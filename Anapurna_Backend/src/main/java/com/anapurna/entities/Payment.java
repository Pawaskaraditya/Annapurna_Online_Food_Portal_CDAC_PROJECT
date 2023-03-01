package com.anapurna.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customerId;
	
	@OneToOne
	@JoinColumn(name="orderId")
	private Orders orderId;
	
	@Column
	private String status;

	public Payment() {
		super();
		
	}

	public Payment(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public Orders getOrderId() {
		return orderId;
	}

	public void setOrderId(Orders orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", customerId=" + customerId + ", orderId=" + orderId + ", status=" + status + "]";
	}
	
	
	
	

}

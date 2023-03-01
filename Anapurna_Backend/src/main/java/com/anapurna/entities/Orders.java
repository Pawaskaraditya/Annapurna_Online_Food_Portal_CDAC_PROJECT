package com.anapurna.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customerId;
	
	@ManyToOne
	@JoinColumn(name="restaurentId")
	private Restaurent restaurentId;
	
	
	@Column
	private String status;
	
	@ManyToOne
	@JoinColumn(name="assigned_to_delivery_person_id")
	private DeliveryPerson assignToDeliveryPersonId;
	
	
	@OneToMany(mappedBy = "orderId")
	private List<OrderItem> orderItems;
	
	@OneToOne(mappedBy = "orderId")
	private Payment payment;
	
	
	public Orders() {
		super();
	}

	public Orders(int id, Customer customerId, Restaurent restaurentId, String status,
			DeliveryPerson assignToDeliveryPersonId, List<OrderItem> orderItems, Payment payment) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.restaurentId = restaurentId;
		this.status = status;
		this.assignToDeliveryPersonId = assignToDeliveryPersonId;
		this.orderItems = orderItems;
		this.payment = payment;
	}

	public Orders(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public Orders(Customer customerId, Restaurent restaurentId, String status,
			DeliveryPerson assignToDeliveryPersonId) {
		super();
		this.customerId = customerId;
		this.restaurentId = restaurentId;
		this.status = status;
		this.assignToDeliveryPersonId = assignToDeliveryPersonId;
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

	public Restaurent getRestaurentId() {
		return restaurentId;
	}

	public void setRestaurentId(Restaurent restaurentId) {
		this.restaurentId = restaurentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DeliveryPerson getAssignToDeliveryPersonId() {
		return assignToDeliveryPersonId;
	}

	public void setAssignToDeliveryPersonId(DeliveryPerson assignToDeliveryPersonId) {
		this.assignToDeliveryPersonId = assignToDeliveryPersonId;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", customerId=" + customerId + ", restaurentId=" + restaurentId + ", status="
				+ status + ", assignToDeliveryPersonId=" + assignToDeliveryPersonId + "]";
	}
	
	
	
	
	
	

}

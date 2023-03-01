package com.anapurna.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Restaurent_Manager")
public class RestaurentManager {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@OneToOne
	@JoinColumn(name="restaurent_Id")
	private Restaurent restaurentId;

	public RestaurentManager() {
		super();
		
	}

	public RestaurentManager(int id, String name, String email, String password, Restaurent restaurentId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.restaurentId = restaurentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Restaurent getRestaurentId() {
		return restaurentId;
	}

	public void setRestaurentId(Restaurent restaurentId) {
		this.restaurentId = restaurentId;
	}

	@Override
	public String toString() {
		return "RestaurentManager [id=" + id + ", name=" + name + ", email=" + email + ", restaurentId=" + restaurentId
				+ "]";
	}
	
	
	
	
	
	
	

}

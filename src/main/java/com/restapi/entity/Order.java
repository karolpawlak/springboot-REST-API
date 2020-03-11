package com.restapi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Setter @Getter
@Table(name = "CUSTOMER_ORDER")
public class Order 
{
	
	private @Id @GeneratedValue Long id;
	private String description;
	private Status status;
	
	public Order() {}
	
	public Order(String description, Status status) 
	{
	
		this.description = description;
		this.status = status;
	}
}

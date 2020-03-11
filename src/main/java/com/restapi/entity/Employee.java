package com.restapi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Setter;
import lombok.Getter;

import lombok.Data;

@Data
@Entity
@Setter @Getter
@Table
public class Employee 
{
	@Id @GeneratedValue
	private Long id;
	private String role;
	private String firstName;
	private String lastName;
	private @OneToOne (cascade = {CascadeType.ALL}) Address address;

	
	public Employee(String firstName, String lastName, String role, String line1, String line2, String city, String county, String postcode)
	{
		this.firstName = firstName;
	 	this.lastName = lastName;
	 	this.role = role;
	 	address = new Address(line1, line2, city, county, postcode);
	}

	public Employee() {}
	
	public void setName(String name)
	{
		String[] parts = name.split(" ");
		this.firstName = parts[0];
		this.lastName = parts[1];
	}
	
	public String getName()
	{
		return this.firstName + " " + this.lastName;
	}
	 
	
}
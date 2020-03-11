package com.restapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Setter @Getter
@Table
public class Address 
{
	private @Id @GeneratedValue Long id;
	private String line1;
	private String line2;
	private String city;
	private String county;
	private String postcode;
	
	public Address() {
		
	}

	
	public Address(String line1, String line2, String city, String county, String postcode) 
	{
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.county = county;
		this.postcode = postcode;
	}


}

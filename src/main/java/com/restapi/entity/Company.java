package com.restapi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Setter @Getter
@Table
public class Company 
{
	private @Id @GeneratedValue Long id;
	private String name;
	private @OneToOne (cascade = {CascadeType.ALL}) Address address;

	public Company(){}
	
	public Company(String name, String line1, String line2, String city, String county, String postcode)
	{
		this.name = name;
		address = new Address(line1, line2, city, county, postcode);
	}
}

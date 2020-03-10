package com.example.tutrest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Data
@Entity
@Table
public class Address {
	private @Id @GeneratedValue Long id;
	private String line1;
	private String city;
	private String county;
	private String postcode;

	Address(){
		
	}
	
	Address(String line1, String city, String county, String postcode){
		this.line1 = line1;
		this.city = city;
		this.county = county;
		this.postcode = postcode;
	}
	
	
}

package com.example.tutrest;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table
class Employee {

	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;
	private String role;
	private @OneToOne(cascade = {CascadeType.ALL}) Address address;
	
	Employee(){
		
	}
	
	Employee(String firstName, String lastName, String role, String line1, String city, String county, String postcode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.address = new Address(line1,city,county,postcode);
	}

	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public void setName(String name) {
		String[] parts =name.split(" ");
		this.firstName = parts[0];
		this.lastName = parts[1];
	}
	

}

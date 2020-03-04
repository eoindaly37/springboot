package com.example.tutrest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table
public class Company {
	private @Id @GeneratedValue Long id;
	private String name;
	private int noemployees;

	Company(){
		
	}
	
	Company(String name, int noemployees){
		this.name = name;
		this.noemployees = noemployees;
	}
}

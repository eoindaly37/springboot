package com.example.tutrest;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	private String line1;
	private String city;
	private String county;
	@Id
	private String postcode;

}

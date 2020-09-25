package com.microservice1.model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GeneratorType;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
@Data
@Entity
@Table(name="employeedata")
public class Employee {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
     int id;
	@Column(nullable=true)
	private String name;
	@Column(unique=true , nullable = false)
	
	private String companyname;
	@Column
	private Long phoneno;

	
	@Column
	private int zipcode;
	@Column(nullable = false)
	private String country;
	@Column(nullable = false)
	private String state;
	@Column(nullable = false)
	private String addressLine1;
	@Column(nullable = false)
	private String addressLine2;
	

	@Column(unique = true ,nullable = false)
     private String emailid;
	
	
	
	 
	

}

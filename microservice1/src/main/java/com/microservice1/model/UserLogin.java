package com.microservice1.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

import lombok.Data;


@Service
@Data
@Entity
@Table(name="employeelogin")
public class UserLogin {

    
	@Id
    int id;
	
    
  @Column(unique=true)
	String emailid;
	@Column
	String password;
	@Column
	String confirmpassword;
	

}

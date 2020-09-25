package com.microservice1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice1.model.Employee;
import com.microservice1.model.Signup;
import com.microservice1.model.UserLogin;

@Service
public interface Service1 {
	public void saveEmployee(Signup emp , UserLogin user);
	public List<Employee>  getEmployeeList();
	public Employee getEmployeeById(int id);
	public void updateEmployee(Employee emp);
	public void deleteEmployee(int id );
	public Employee userLoginVerificatio(UserLogin ul );
	

}

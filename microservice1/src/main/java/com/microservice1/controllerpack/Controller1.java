package com.microservice1.controllerpack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice1.NullFeild;
import com.microservice1.model.CreateBean;
import com.microservice1.model.Employee;
import com.microservice1.model.Signup;
import com.microservice1.model.UserLogin;
import com.microservice1.service.Service1;

@RestController
@RequestMapping("microservice")
public class Controller1 {
	@Autowired
	Service1 service;
	@Autowired
	UserLogin ul;
	@Autowired
	CreateBean decode;

	@PostMapping("account")
	public ResponseEntity<String> saveRequest(@RequestBody Signup emp) {
		
		
		
	
		service.saveEmployee(emp , ul);
	
		
		return new ResponseEntity<String>("Account is created", HttpStatus.CREATED);

	}

		@GetMapping("account/{id}")
	public ResponseEntity<Employee> getSingleEmployee(@PathVariable int id) {
		System.out.println("idCheck" + id);
		Employee emp = service.getEmployeeById(id);

		return new ResponseEntity<Employee>(emp, HttpStatus.FOUND);

	}
		
		
		
		
		@PostMapping("accountlogin")
		public ResponseEntity<Employee> userLogin(@RequestHeader String Authorization) {
			System.out.println("Header="+ Authorization);
		UserLogin ul1 =	 decode.decodeAuthorization(Authorization);
		
		System.out.println(ul1.getEmailid()+""+ul1.getPassword());
		
			
          Employee emp = service.userLoginVerificatio(ul1);
          
          
			
		return	new ResponseEntity<Employee>(emp, HttpStatus.FOUND);
		}


	@PutMapping("account")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee emp) {
		System.out.println("Check3");
		service.updateEmployee(emp);
		return new ResponseEntity<String>("Data is update", HttpStatus.OK);

	}

	@DeleteMapping("account/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int id) {

		service.deleteEmployee(id);
		return new ResponseEntity<String>("Data is Deleted", HttpStatus.OK);

	}
}
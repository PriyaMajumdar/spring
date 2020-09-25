package com.microservice1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.microservice1.EmailFormat;
import com.microservice1.IdNotFound;
import com.microservice1.NullFeild;
import com.microservice1.PasswordNotMatch;
import com.microservice1.dao.Dao1;
import com.microservice1.model.CreateBean;
import com.microservice1.model.Employee;
import com.microservice1.model.Signup;
import com.microservice1.model.UserLogin;

@Component
public class Servicei1imp implements Service1 {
	@Autowired
	Dao1 dao;
	@Autowired
	Employee e;

	@Autowired
	CreateBean generatepassword;

	@Override
	@Transactional
	public void saveEmployee(Signup emp, UserLogin ul) {
		// TODO Auto-generated method stub
		Boolean flag = false;

		if (emp.getEmailid() != null && emp.getPassword() != null && emp.getConfirmpassword() != null) {
			System.out.println("check 9 email id =" + emp.getEmailid());
			String password = emp.getPassword();

			String confirmpassword = emp.getConfirmpassword();
			if (password.equals(confirmpassword)) {

				password = generatepassword.generatePassword(password);
				System.out.println("check110:generatepassword" + password);

				String emailid = emp.getEmailid();
				int length = emailid.length();
				--length;
				

				System.out.println("Check 6");
				
				// Email format checking......................................................
				if (emailid.charAt(0) != '@' && emailid.charAt(length) != '@') {
					for (int j = 1; j < length; j++) {
						if (emailid.charAt(j) == '@') {

							ul.setEmailid(emp.getEmailid());
							// Setting generated password using generatePassword().....................
							ul.setPassword(password);
							ul.setConfirmpassword(password);

							e.setName(emp.getName());
							e.setEmailid(emp.getEmailid());
							e.setState(emp.getState());
							e.setPhoneno(emp.getPhoneno());
							e.setZipcode(emp.getZipcode());
							e.setPhoneno(emp.getPhoneno());
							e.setCountry(emp.getCountry());
							e.setAddressLine1(emp.getAddressLine1());
							e.setCompanyname(emp.getCompanyname());
							e.setAddressLine2(emp.getAddressLine2());

							dao.saveEmployee(e, ul);

							flag = true;
							break;

						} // if end

					} // for end

				} // if end

				else {
					throw new EmailFormat();

				} // if email @ check (first and last @ check)
				if (flag == false) {

					throw new EmailFormat();
				}

			} else {

				System.out.println("Check 7");
				throw new PasswordNotMatch();
			}
		} else {
			System.out.println("Check : nullfeild in serviceimp saveEmployee");
			throw new NullFeild();

		} // if (null)

	}

	@Transactional
	public List<Employee> getEmployeeList() {
		List<Employee> li = dao.getEmployeeList();
		return li;

	}

	@Transactional
	public void updateEmployee(Employee emp) {
		dao.updateEmployee(emp);

	}

	@Transactional
	public void deleteEmployee(int id) {
		dao.deleteEmployee(id);

	}

	public Employee getEmployeeById(int id) {
		Employee emp = dao.getEmployeeById(id);
		if (emp == null) {
			throw new IdNotFound();
		}
		return emp;

	}

	@Override

	@Transactional
	public Employee userLoginVerificatio(UserLogin ul1) {
		// TODO Auto-generated method stub

		String password = generatepassword.generatePassword(ul1.getPassword());

		ul1.setPassword(password);

		Employee e1 = dao.userLoginVerification(ul1);
		return e1;

	}

}

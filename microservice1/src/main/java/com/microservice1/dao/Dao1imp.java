package com.microservice1.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import com.microservice1.IdNotFound;
import com.microservice1.NullFeild;
import com.microservice1.SameCompanyName;
import com.microservice1.SameEmailId;
import com.microservice1.UnAuthorisedAccess;
import com.microservice1.model.Employee;
import com.microservice1.model.UserLogin;

@Component
public class Dao1imp implements Dao1 {
	@Autowired
	private EntityManager em;

	public void saveEmployee(Employee e, UserLogin ul) {

		try {
			Session cs = em.unwrap(Session.class);
			Query<Employee> q = cs.createQuery("from Employee", Employee.class);
			List<Employee> li = q.getResultList();
			Iterator<Employee> it = li.iterator();
			while (it.hasNext()) {

				Employee em = it.next();
				if (em.getCompanyname().equals(e.getCompanyname())) {
					System.out.println("check1");

					throw new SameCompanyName();

				}

			}

			cs.flush();
			cs.clear();
			System.out.println("Check 79");

			Query<UserLogin> q1 = cs.createQuery("from UserLogin", UserLogin.class);
			List<UserLogin> li1 = q1.getResultList();
			Iterator<UserLogin> it1 = li1.iterator();
			while (it1.hasNext()) {
				System.out.println("check : 80 , userlogin data");

				UserLogin user = it1.next();
				if (user.getEmailid().equals(ul.getEmailid())) {
					System.out.println("check5");

					throw new SameEmailId();

				}

			}

			cs.flush();
			cs.clear();
			System.out.println("check:" + e.getName());
			cs.save(e);

			System.out.println("Employee is save");
			cs.flush();
			cs.clear();

			String sql = "select * from employeedata where emailid=" + "'" + e.getEmailid() + "'";

			System.out.println("Check 81");

			Query<Employee> q3 = cs.createNativeQuery(sql, Employee.class);
			List<Employee> li2 = q3.getResultList();
			Iterator<Employee> it2 = li2.iterator();
			if (it2.hasNext()) {
				System.out.println("check 40");

				e = it2.next();
				System.out.println("check 50");

			}
			ul.setId(e.getId());

			cs.save(ul);

		} catch (SameCompanyName scn) {
			throw new SameCompanyName();

		} catch (SameEmailId ex) {
			throw new SameEmailId();
		} catch (Exception ex) {
			throw new NullFeild();
		}

	}

	@Override
	public List<Employee> getEmployeeList() {
		// TODO Auto-generated method stub
		Session cs = em.unwrap(Session.class);
		Query<Employee> q = cs.createQuery("from Employee", Employee.class);
		List<Employee> li = q.getResultList();

		return li;

	}

	public Employee getEmployeeById(int id) {
		Session cs = em.unwrap(Session.class);
		Employee emp = cs.get(Employee.class, id);
		return emp;

	}

	public void updateEmployee(Employee emp) {

		Session cs = em.unwrap(Session.class);
		Query<Employee> q = cs.createQuery("from Employee", Employee.class);
		List<Employee> li = q.getResultList();
		Iterator<Employee> it = li.iterator();
		while (it.hasNext()) {

			Employee em = it.next();
			if (em.getCompanyname().equals(emp.getCompanyname()) && em.getId() != emp.getId()) {
				System.out.println("check4");

				throw new SameCompanyName();

			}

		}
		cs.flush();
		cs.clear();

		cs.update(emp);

	}

	public void deleteEmployee(int id) {

		Session cs = em.unwrap(Session.class);
		Employee emp = cs.get(Employee.class, id);
		if (emp == null) {
			throw new IdNotFound();

		} else {
			cs.delete(emp);
		}

	}

	@Override
	public Employee userLoginVerification(UserLogin ul) {
		// TODO Auto-generated method stub

		Employee e2 = null;
		if (ul.getEmailid() != null && ul.getPassword() != null) {
			String password = ul.getPassword();
			Session cs = em.unwrap(Session.class);
			String eid1 = ul.getEmailid();
			
			String sql = "select * from employeelogin where emailid=" + "'" + eid1 + "' and password = " + "'"
					+ password + "'";

			Query<UserLogin> q3 = cs.createNativeQuery(sql, UserLogin.class);

			
			UserLogin ul2 = null;

			List<UserLogin> li = q3.getResultList();
			Iterator<UserLogin> it = li.iterator();
			while (it.hasNext()) {
				System.out.println("check 10");

				ul2 = it.next();
				System.out.println("check 20");

			}

			cs.flush();
			cs.clear();
			System.out.println("check 15");

			if (ul2 != null) {
				String eid = ul2.getEmailid();

				String sql2 = "select * from employeedata where emailid=" + "'" + eid + "'";
				System.out.println("check 11");

				Query<Employee> q2 = cs.createNativeQuery(sql2, Employee.class);
				List<Employee> li2 = q2.getResultList();
				Iterator<Employee> it2 = li2.iterator();
				while (it2.hasNext()) {
					System.out.println("Check 25");

					e2 = it2.next();
				}

			} else {
				System.out.println("check 12");
				throw new UnAuthorisedAccess();

			}

		} else {
			throw new NullFeild();

		}

		return e2;

	}

}

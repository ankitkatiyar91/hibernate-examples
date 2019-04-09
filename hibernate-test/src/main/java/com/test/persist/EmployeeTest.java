package com.test.persist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.test.Employee;

public class EmployeeTest {
	public static void main(String[] args) {
		final SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();

		Session session = factory.openSession();

		session.beginTransaction();
		Employee employee = new Employee();
		employee.setName("Ankit");

		
		session.save(employee);
		session.getTransaction().commit();
		session.close();
	}
}
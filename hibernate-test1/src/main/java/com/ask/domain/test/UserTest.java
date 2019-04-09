package com.ask.domain.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ask.domain.User;

public class UserTest {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		User user = new User();
		user.setId(1);
		user.setName("Ankit");

		Session session = factory.openSession();

		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();

		session.close();
		//factory.close();

	}
}

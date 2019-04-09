package com.test.persist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.test.User;

public class UserTest {

	public static void main(String[] args) {
		final SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();

		Session session = factory.openSession();
		session.beginTransaction();
		User user = new User();
		user.setName("Ankit");
		session.save(user);
		session.getTransaction().commit();
		session.close();

	}
}

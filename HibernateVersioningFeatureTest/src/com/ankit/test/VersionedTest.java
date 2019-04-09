package com.ankit.test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ankit.dto.Address;

public class VersionedTest {
	
	public static void main(String[] args) {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		
		Session session=factory.openSession();
		session.beginTransaction();
		Address address=new Address();
		address.setCity("city1");
		address.setCountry("country1");
		address.setStreet("street1");
		session.save(address);
		System.out.println("Added->"+address);
		address.setCity("city2");
		address.setCountry("country2");
		address.setStreet("street2");
		session.save(address);
		System.out.println("Added->"+address);
		
		session.save(address);
		System.out.println("Added->"+address);
		session.getTransaction().commit();
		
		session=factory.openSession();
		session.beginTransaction();
		
		address.setCity("city3");
		address.setCountry("country3");
		address.setStreet("street3");
		session.saveOrUpdate(address);
		System.out.println("Added->"+address);
		session.getTransaction().commit();
		
		/*
		 * Query for version update
		 */
		session=factory.openSession();
		session.beginTransaction();
		Query query=session.createQuery("update VERSIONED Address set city='mycity' where addressId=1");
		
		query.executeUpdate();
		
		System.out.println("Added->"+address);
		session.getTransaction().commit();
	}

}

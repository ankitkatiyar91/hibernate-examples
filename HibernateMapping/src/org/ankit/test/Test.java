package org.ankit.test;

import org.ankit.dto.Address;
import org.ankit.dto.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session= sessionFactory.openSession();
		try {
			
			UserDetails userDetails=new UserDetails();
			userDetails.setUserName("Username");
			
			Address address=new Address();
			address.setCity("New Delhi");
			address.setState("Delhi");
			address.setStreet("Hauz khas");
			
			
			Address address2=new Address();
			address2.setCity("New Delhi");
			address2.setState("Delhi");
			address2.setStreet("Hauz khas2");
			
			
			session.beginTransaction();
			session.save(userDetails);
			
			userDetails.getAddresses().add(address);
			userDetails.getAddresses().add(address2);
			
			session.save(address);
			session.save(address2);
			
			userDetails=new UserDetails();
			userDetails.setUserName("Username2");
			
			 address=new Address();
			address.setCity("New Delhi");
			address.setState("Delhi");
			address.setStreet("Saket");
			
			
			userDetails.getAddresses().add(address);
			
			
			session.save(userDetails);
			
			session.save(address);
			
			session.getTransaction().commit();
			
		System.out.println("User added");
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
	}

}

package org.ankit.test;

import java.util.Date;

import org.ankit.dto.Address;
import org.ankit.dto.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EmbeddedTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory factory =new Configuration().configure().buildSessionFactory();
		Session session=factory.openSession();
		
		session.beginTransaction();
		
		UserDetails user=new UserDetails();
		user.setDescription("Description");
		Address address=new Address();
		address.setCity("Delhi");
		address.setState("New Delhi");
		address.setStreet("James Thomson");
		
		Address homeAddress=new Address();
		homeAddress.setCity(" home Delhi");
		homeAddress.setState("home New Delhi");
		homeAddress.setStreet(" home James Thomson");
		
		user.getAddresses().add(address);
		user.getAddresses().add(homeAddress);
		
		user.setJoinedDate(new Date());
		user.setUserName("Hey yaaaaa!!!!!!");

		session.save(user);
		session.getTransaction().commit();
		session.close();
		System.out.println("Saved User");
	}

}

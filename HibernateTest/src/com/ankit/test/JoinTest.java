package com.ankit.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.ankit.dto.Address;
import com.ankit.dto.UserDetails;

public class JoinTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 Configuration configuration = new Configuration();
	        configuration.configure();
	       ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).build();        
	        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	        
	        Session session=sessionFactory.openSession();
	        session.beginTransaction();
	        
	        UserDetails details=new UserDetails();
	        details.setUserName("Ankit");
	        
	        Address address=new Address();
	        address.setCity("city");
	        address.setCountry("country");
	        address.setStreet("street");
	        
	        details.getAddresses().add(address);
	        
	        
	        Address addres1s=new Address();
	        addres1s.setCity("city1");
	        addres1s.setCountry("country1");
	        addres1s.setStreet("street1");
	        
	        details.getAddresses().add(addres1s);
	        
	        session.save(address);
	        session.save(addres1s);
	        session.save(details);
	        
	        session.getTransaction().commit();
	        session.close();

	}

}

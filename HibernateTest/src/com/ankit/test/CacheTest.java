/**
 * 
 */
package com.ankit.test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.ankit.dto.UserDetails;

/**
 * @author ipg
 * 
 */
public class CacheTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		checkNormalCache();
		//checkQueryCahce();
		System.out.println("Closed");
	}
	
	private static void checkQueryCahce() {

		 Configuration configuration = new Configuration();
	        configuration.configure();
	       ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).build();        
	        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		
		/*
		 * Add Object
		 */
		Session session=sessionFactory.openSession();
		session.beginTransaction();		
		UserDetails details;
		for (int i = 0; i < 5; i++) {
			details=new UserDetails();
			details.setUserName("userName"+i);
			session.save(details);
		}
		session.getTransaction().commit();
		session.close();
		System.out.println("Session Closed");
		
		/*
		 * First Time fetch
		 */
		session=sessionFactory.openSession();
		Query query=session.createQuery("FROM userDetails");
		query.setCacheable(true);
		java.util.List<UserDetails> list=query.list();
		System.out.println("Objects in first fetch-->");
		for (UserDetails userDetails : list) {
			System.out.println(userDetails);
		}
		
		session.close();
		
		/*
		 * Retry after session close
		 */
		
		System.out.println("Session Closed now retry again");
		
		session=sessionFactory.openSession();	
		query=session.createQuery("FROM userDetails");
		query.setCacheable(true);
		
		list=query.list();		
		for (UserDetails userDetails : list) {
			System.out.println(userDetails);
		}		
		session.close();
	

	}
	
	private static void checkNormalCache() {

		
		SessionFactory sessionFactory =new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		UserDetails details;
		for (int i = 0; i < 5; i++) {
			details=new UserDetails();
			details.setUserName("userName"+i);
			session.save(details);
		}
		
		System.out.println(session.get(UserDetails.class, 1));
		session.getTransaction().commit();
		session.close();
		System.out.println("Session Closed");
		
		session=sessionFactory.openSession();
		
		System.out.println(session.get(UserDetails.class, 1));
		System.out.println(session.get(UserDetails.class, 2));
		System.out.println(session.get(UserDetails.class, 5));
		/*java.util.List<UserDetails> list=session.createQuery("FROM userDetails").list();
		
		for (UserDetails userDetails : list) {
			System.out.println(userDetails);
		}*/
		
		session.close();
		
		
		System.out.println("Session Closed now retry again");
		
		for (int i = 1; i <=5; i++) {
			session = sessionFactory.openSession();
			System.out.println(i+"  "+ session.get(UserDetails.class, i));
			/*list=session.createQuery("FROM userDetails").list();
			
			for (UserDetails userDetails : list) {
				System.out.println(userDetails);
			}*/
			session.close();
		}
	
	}
}

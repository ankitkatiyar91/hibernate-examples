package org.ankit.test;

import java.util.Date;

import org.ankit.dto.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddUserDetails {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserDetails userDetails=new UserDetails();
		
		userDetails.setUserName("Second User");
		userDetails.setJoinedDate(new Date());
		userDetails.setDescription("Second user description");
		
		UserDetails userDetails2=new UserDetails();
		
		userDetails2.setUserName("Third User");
		userDetails2.setJoinedDate(new Date());
		userDetails2.setDescription("Third user description");
		
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session= sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(userDetails);
			session.save(userDetails2);
			for (int i = 0; i < 5; i++) {
				
				userDetails2=new UserDetails();
				
				userDetails2.setUserName("Third User "+i);
				userDetails2.setJoinedDate(new Date());
				userDetails2.setDescription("Third user description");
				session.save(userDetails2);
				System.out.println("Object 2 saved ");
			}
			session.getTransaction().commit();
			System.out.println("User added");
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		
		try {
			session=sessionFactory.openSession();
			session.beginTransaction();
			userDetails2=(UserDetails) session.get(UserDetails.class, 3);
			
			System.out.println(userDetails2);
			userDetails2.setDescription("Auto updated");
			System.out.println(userDetails2);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}

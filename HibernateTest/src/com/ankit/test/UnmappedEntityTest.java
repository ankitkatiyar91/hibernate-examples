package com.ankit.test;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ankit.dto.User;
import com.ankit.util.UserResultTransformer;

public class UnmappedEntityTest {

	public static void main(String[] args) {
		 Configuration configuration = new Configuration();
	        configuration.configure();
	        SessionFactory factory=configuration.buildSessionFactory();
	        Session session=factory.openSession();
	        session.beginTransaction();
	        
	        Query query=session.createSQLQuery("SELECT * FROM user");
	        query.setResultTransformer(new UserResultTransformer());
	        System.out.println(query.getQueryString());
	        System.out.println(query.list());
	        
	        
	        Criteria criteria=session.createCriteria(User.class);
	        criteria.setResultTransformer(new UserResultTransformer());
	        System.out.println(criteria.toString());
	        List<User>  list=criteria.list();
	        
	        System.out.println("user List->"+list);
	        for (User user : list) {
				System.out.println(user);
			}
	        
	        session.getTransaction().commit();session.close();
//	        /query.get

	}

}

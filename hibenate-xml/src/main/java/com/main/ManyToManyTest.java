package com.main;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.domain.Owner;
import com.domain.RentalCar;

public class ManyToManyTest {

	private static SessionFactory factory;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		System.out.println("ManyToManyTest.main()");
		factory = new Configuration().configure().buildSessionFactory();
		//testAddition();
		testFetchWithQuery();
		//testFetch();
	}

	private static void testAddition(){
		System.out.println("Factory created");
		Session session=factory.openSession();
		
		RentalCar car=new RentalCar();
		car.setCarName("car1");
		
		RentalCar car2=new RentalCar();
		car2.setCarName("car2");
		
		Owner owner=new Owner();
		owner.setName("Owner");
		owner.getCars().add(car);
		owner.getCars().add(car2);
		
		Owner owner2=new Owner();
		owner2.setName("Owner");
		owner2.getCars().add(car);
		
		car.getOwners().add(owner);
		car.getOwners().add(owner2);
		
		session.beginTransaction();
		session.persist(car);
		session.persist(car2);
		
		session.persist(owner);
		session.persist(owner2);
		
		session.getTransaction().commit();
		System.out.println("All is done");
		session.close();
	}


	private static void testFetch() {
		Session session = factory.openSession();
		RentalCar car=(RentalCar) session.get(RentalCar.class, 1);
		System.out.println("List-" + car.getOwners());
		session.close();

		session = factory.openSession();
		car=(RentalCar) session.get(RentalCar.class, 1);
		System.out.println("List-" + car.getOwners());
		session.close();
	}
	
	private static void testFetchWithQuery() {
		Session session = factory.openSession();
		Query query = session.getNamedQuery("GetOwners");
		query.setParameter(0, 1);
		System.out.println("List-" + query.list());
		session.close();

		session = factory.openSession();
		query = session.getNamedQuery("GetOwners");
		query.setParameter(0, 1);
		System.out.println("List-" + query.list());
		session.close();
	}

}

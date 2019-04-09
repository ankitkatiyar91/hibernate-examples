package com.main;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.xml.sax.helpers.DefaultHandler;

import com.annon.domain.Owner;
import com.annon.domain.RentalCar;

public class ManyToManyAnnonTest {

	private static SessionFactory factory;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			System.out.println("ManyToManyTest.main()");
			/*SAXParser  parser=SAXParserFactory.newInstance().newSAXParser();
			parser.parse(new File("src/main/resources/ehcache.xml"), new DefaultHandler());*/
			factory = new Configuration().configure().buildSessionFactory();
			// testAddition();
			// testFetchWithQuery();
			//testFetchWithNamedQuery();
			// testFetchWithNativeQuery();
			//testFetch();
			testCriteria();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			factory.close();
		}
	}

	@SuppressWarnings("unused")
	private static void testAddition() {
		System.out.println("ManyToManyAnnonTest.testAddition()");
		Session session = factory.openSession();

		RentalCar car = new RentalCar();
		car.setCarName("car1");

		RentalCar car2 = new RentalCar();
		car2.setCarName("car2");

		Owner owner = new Owner();
		owner.setName("Owner");
		owner.getCars().add(car);
		owner.getCars().add(car2);

		/*
		 * Owner owner2=new Owner(); owner2.setName("Owner2");
		 * owner2.getCars().add(car);
		 */

		car.getOwners().add(owner);
		// car.getOwners().add(owner2);

		session.beginTransaction();
		session.persist(car);
		session.persist(car2);

		session.persist(owner);
		// session.persist(owner2);

		session.getTransaction().commit();
		System.out.println("All is done");
		session.close();
	}

	@SuppressWarnings("unused")
	private static void testFetch() {
		System.out.println("ManyToManyAnnonTest.testFetch()");
		Session session = factory.openSession();
		RentalCar car = (RentalCar) session.get(RentalCar.class, 1);
		System.out.println("List-" + car.getOwners());
		session.close();

		showCacheStatus();

		session = factory.openSession();
		car = (RentalCar) session.get(RentalCar.class, 1);
		System.out.println("List-" + car.getOwners());
		session.close();

		showCacheStatus();
	}

	@SuppressWarnings("unused")
	private static void testFetchWithNamedQuery() throws InterruptedException {
		System.out.println("ManyToManyAnnonTest.testFetchWithNamedQuery()");

		showCacheStatus();

		Session session = factory.openSession();
		Query query = session.getNamedQuery("GetOwners");
		query.setParameter(0, 1);
		System.out.println("List-" + query.list());
		System.out.println("Cache Region-" + query.getCacheRegion());
		System.out.println("contains-" + session.contains(query.list().get(0)));
		session.close();

		showCacheStatus();

		Thread.sleep(300);
		System.err.println("%%%%%%%%%%%%%%%%%% second phase start %%%%%%%%%%%%%%%%%%%%%%%");

		session = factory.openSession();
		query = session.getNamedQuery("GetOwners");
		query.setParameter(0, 1);

		System.out.println("List-" + query.list());
		System.out.println("Cache Region-" + query.getCacheRegion());
		showCacheStatus();
		session.close();
	}

	@SuppressWarnings("unused")
	private static void testFetchWithQuery() {
		System.out.println("ManyToManyAnnonTest.testFetchWithQuery()");

		Session session = factory.openSession();
		Query query = session.createQuery("select r.owners from RentalCar r where  r.carId=?");
		query.setCacheable(true);
		query.setParameter(0, 1);
		System.out.println("List-" + query.list());
		session.close();

		session = factory.openSession();
		query = session.createQuery("select r.owners from RentalCar r where  r.carId=?");
		query.setCacheable(true);
		query.setParameter(0, 1);
		System.out.println("List-" + query.list());
		session.close();
	}

	@SuppressWarnings("unused")
	private static void testFetchWithNativeQuery() {
		System.out.println("ManyToManyAnnonTest.testFetchWithNativeQuery()");
		Session session = factory.openSession();
		Query query = session.getNamedQuery("GetOwnersByNative");
		query.setParameter(0, 1);
		System.out.println("List-" + query.list());
		session.close();

		session = factory.openSession();
		query = session.getNamedQuery("GetOwnersByNative");
		query.setParameter(0, 1);
		System.out.println("List-" + query.list());
		session.close();
	}

	private static void showCacheStatus() {
		System.out.println("%%%%%%%%%%%%% DETAILS OF CACHE %%%%%%%%%%%%%%%");
		org.hibernate.Cache cache = factory.getCache();
		// net.sf.ehcache.CacheManager
		// cacheManager=net.sf.ehcache.CacheManager.getInstance();
		System.out.println("Query cache hit count-" + factory.getStatistics().getQueryCacheHitCount());
		System.out.println("Query cache miss count-" + factory.getStatistics().getQueryCacheMissCount());
		System.out.println("Query cache put count-" + factory.getStatistics().getQueryCachePutCount());
		System.out.println("Query cache execution count-" + factory.getStatistics().getQueryExecutionCount());
		System.out.println("factory.getStatistics().getCollectionLoadCount()--" + factory.getStatistics().getCollectionLoadCount());
		System.out.println("factory.getStatistics().getCollectionFetchCount()--" + factory.getStatistics().getCollectionFetchCount());
		System.out.println("Contains Owner 1-" + cache.containsEntity(Owner.class, 1));
		System.out.println("Contains Car 1-" + cache.containsEntity(RentalCar.class, 1));

	}
	
	private static void testCriteria() {

		System.out.println("ManyToManyAnnonTest.testFetch()");
		Session session = factory.openSession();
		
		System.out.println("List-" + session.createCriteria(RentalCar.class).add(Restrictions.eq("carId", 1)).setCacheable(true).list());
		session.close();

		//showCacheStatus();

		session = factory.openSession();
		System.out.println("List-" + session.createCriteria(RentalCar.class).add(Restrictions.eq("carId", 1)).setCacheable(true).list());
		session.close();

		//showCacheStatus();
	

	}
}

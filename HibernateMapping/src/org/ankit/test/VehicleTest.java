package org.ankit.test;

import java.util.List;

import org.ankit.dto.Owner;
import org.ankit.dto.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class VehicleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration configuration=new Configuration();
		
		configuration.configure();
		@SuppressWarnings("deprecation")
		SessionFactory factory=configuration.buildSessionFactory();
		Session session=factory.openSession();
		session.beginTransaction();
		
		
		 
		
		Vehicle  vehicle=new Vehicle();
		vehicle.setVehicleName("Car");
		session.save(vehicle);
		
		Vehicle  vehicle1=new Vehicle();
		vehicle1.setVehicleName("Byke");
		session.save(vehicle1);

		/*owner.getVehicles().add(vehicle);
		owner.getVehicles().add(vehicle1);*/
		
		Owner owner=new Owner();
		owner.setName("Ankit Singh Katiyar");
		owner.getVehicles().add(vehicle);
		session.save(owner);
		
		Owner owner1=new Owner();
		owner1.setName("Owner 1");
		owner1.getVehicles().add(vehicle1);
		owner1.getVehicles().add(vehicle);
		session.save(owner1);
		
		vehicle.setOwner(owner);
		vehicle1.setOwner(owner1);
		
		System.out.println("Every thing updated");
		session.getTransaction().commit();
		
		session.close();
		session=factory.openSession();
		
		@SuppressWarnings("unchecked")
		List<Owner> owners= (List<Owner>) (session.createQuery("FROM OWNER").list() );
		
		for (Owner owner2 : owners) {
			System.out.println("Owner "+owner2+" Vehicle-"+owner2.getVehicles());
		}
		
		
		session.close();
	}

}


package org.ankit.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity(name="OWNER")
public class Owner {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int ownerId;
	String name;
	@OneToMany(mappedBy="owner")
	//@JoinTable(name="Vehicles_user",inverseJoinColumns=@JoinColumn(name="vehicleId"))
	private List<Vehicle> vehicles=new ArrayList<Vehicle>();;
	
	
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}

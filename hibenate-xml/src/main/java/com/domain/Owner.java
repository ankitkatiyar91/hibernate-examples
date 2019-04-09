package com.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ipg
 * 
 */
public class Owner {

	private Integer ownerId;
	private String name;
	private List<RentalCar> cars = new ArrayList<RentalCar>();

	public Integer getOwnerId() {
		return ownerId;
	}

	@Override
	public String toString() {
		return "Owner [ownerId=" + ownerId + ", name=" + name + "]";
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RentalCar> getCars() {
		return cars;
	}

	public void setCars(List<RentalCar> cars) {
		this.cars = cars;
	}

}

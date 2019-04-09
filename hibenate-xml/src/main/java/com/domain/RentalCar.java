package com.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RentalCar {

	private Integer carId;

	private String carName;
	private List<Owner> owners = new ArrayList<Owner>();

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public List<Owner> getOwners() {
		return owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}

}

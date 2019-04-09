package com.annon.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author ipg
 * 
 */
@Entity
@Table(name = "anon_owner")
@Cacheable(value = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="yourEntityCache")
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ownerId;
	private String name;

	@ManyToMany(targetEntity = RentalCar.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "annon_car_owner", joinColumns = { @JoinColumn(nullable = false, name = "ownerId") }, inverseJoinColumns = { @JoinColumn(nullable = false, name = "carId") })
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="yourCollectionRegion")
	private List<RentalCar> cars = new ArrayList<RentalCar>();

	public Integer getOwnerId() {
		return ownerId;
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

	@Override
	public String toString() {
		return "Owner [ownerId=" + ownerId + ", name=" + name + "]";
	}

}

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
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "annon_rental_car")
@Cacheable(value = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="yourEntityCache")
@NamedQueries(value = { @NamedQuery(name = "GetOwners", query = "select r.owners from RentalCar r where  r.carId=?", hints = { @QueryHint(name = "org.hibernate.cacheable", value = "true") }) })
@NamedNativeQueries(value = { @NamedNativeQuery(name = "GetOwnersByNative", query = "SELECT DISTINCT ownerId as ownerId,name as name FROM anon_owner ao JOIN annon_car_owner aco WHERE aco.cars_carId=?", hints = { @QueryHint(name = "org.hibernate.cacheable", value = "true") }) })
public class RentalCar {

	@Override
	public String toString() {
		return "RentalCar [carId=" + carId + ", carName=" + carName + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer carId;

	private String carName;

	@ManyToMany(mappedBy = "cars", fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Owner.class)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="yourCollectionRegion")
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

/**
 * 
 */
package org.ankit.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Ankit
 *
 */
@Entity(name="vehicle")
@Table(name="vehicle")
public class Vehicle {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int vehicleId;
	String vehicleName;
	@ManyToOne()
	Owner owner;
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleName="
				+ vehicleName + ", owner=" + owner + ", getOwner()="
				+ getOwner() + ", getVehicleId()=" + getVehicleId()
				+ ", getVehicleName()=" + getVehicleName() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	

}

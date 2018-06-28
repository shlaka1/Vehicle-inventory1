package com.vehicle.Restwebservice.vehicleRestwebservice;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue
	private int id;
	private String vehicleName;
	private String  vehicleType;
	
	protected Vehicle() {
		
	}

	public Vehicle(int id, String vehicleName, String vehicleType) {
		super();
		this.id = id;
		this.vehicleName = vehicleName;
		this.vehicleType = vehicleType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", vehicleName=" + vehicleName + ", vehicleType=" + vehicleType + "]";
	}
	
}


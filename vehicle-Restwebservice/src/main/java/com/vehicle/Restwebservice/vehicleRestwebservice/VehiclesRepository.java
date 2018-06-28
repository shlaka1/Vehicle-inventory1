package com.vehicle.Restwebservice.vehicleRestwebservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VehiclesRepository extends JpaRepository <Vehicle, Integer>{
	

}

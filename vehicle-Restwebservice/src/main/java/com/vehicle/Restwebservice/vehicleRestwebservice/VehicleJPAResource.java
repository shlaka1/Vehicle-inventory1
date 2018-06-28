package com.vehicle.Restwebservice.vehicleRestwebservice;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class VehicleJPAResource  {

	@Autowired
	private VehiclesRepository vehiclesRepository;

	//To get all vehicles from database-h2
	@GetMapping("/jpa/vehicles")
	public List<Vehicle> retrieveAllVehicles() {
		return vehiclesRepository.findAll();
	}

	//To search(retrieve) vehicle with particular id
	@GetMapping("/jpa/vehicles/{id}")
	public Resource <Vehicle> retrieveVehicle(@PathVariable int id) {
		Optional<Vehicle> v= vehiclesRepository.findById(id);
		if (!v.isPresent())
		throw new VehicleNotFoundException("id-" + id);
		Resource<Vehicle> resource = new Resource <Vehicle>(v.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllVehicles());
		resource.add(linkTo.withRel("all-vehicles"));
		return resource;
	}

	//To delete vehicle with particular id
	@DeleteMapping("/jpa/deletevehicles/{id}")
	public void deleteVehicle(@PathVariable int id) {
		vehiclesRepository.deleteById(id);
	}

	//To add vehicle 
	@PostMapping("/jpa/addvehicles")
	 public ResponseEntity<Object> addVehicle(@Valid @RequestBody Vehicle vehicle) {
		Vehicle savedVehicle = vehiclesRepository.save(vehicle);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedVehicle.getId()).toUri();
		return ResponseEntity.created(location).build();

	}
	
	//Updating vehicle using its id
	 @PutMapping("/jpa/updatevehicles/{id}")
	public ResponseEntity<Object> updateVehicle(@PathVariable (value = "id") int id, @Valid @RequestBody Vehicle vehicle) {

		Optional<Vehicle> v = vehiclesRepository.findById(id);
		if (!v.isPresent())
		throw new VehicleNotFoundException("id-" + id);
		vehicle.setVehicleName(vehicle.getVehicleName());
		vehicle.setVehicleType(vehicle.getVehicleType());		
		Vehicle updated= vehiclesRepository.save(vehicle);
		//return updated;
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updated.getId()).toUri();
		return ResponseEntity.created(location).build();

	}
}

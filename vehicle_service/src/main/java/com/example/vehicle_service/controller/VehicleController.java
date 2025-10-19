package com.example.vehicle_service.controller;

import com.example.vehicle_service.model.Vehicle;
import com.example.vehicle_service.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    //Get vehicles by service year
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Vehicle>> getVehiclesByYear(@PathVariable int year) {
        List<Vehicle> vehicles = vehicleRepository.findByServiceYear(year);
        if (vehicles.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(vehicles);
    }

    //Get vehicle type by service ID
    @GetMapping("/type/{id}")
    public ResponseEntity<String> getVehicleTypeById(@PathVariable Long id) {
        String type = vehicleRepository.findVehicleTypeByServiceId(id);
        if (type == null) {
            return ResponseEntity.status(404).body("Vehicle type not found for ID: " + id);
        }
        return ResponseEntity.ok(type);
    }

    //Delete all vehicles by service year
    @DeleteMapping("/year/{year}")
    public ResponseEntity<String> deleteVehiclesByYear(@PathVariable int year) {
        vehicleRepository.deleteByServiceYear(year);
        return ResponseEntity.ok("All vehicles serviced in year " + year + " deleted successfully.");
    }

    //Delete a single vehicle by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicleById(@PathVariable Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return ResponseEntity.ok("Vehicle with service ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Vehicle with service ID " + id + " not found.");
        }
    }

    //Add new vehicle
    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return ResponseEntity.ok(savedVehicle);
    }

    //Get all vehicles
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleRepository.findAll());
    }

    //Update an existing vehicle
    @PutMapping("/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable Long id, @RequestBody Vehicle updatedVehicle) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);

        if (existingVehicle.isPresent()) {
            Vehicle vehicle = existingVehicle.get();
            vehicle.setVehicleType(updatedVehicle.getVehicleType());
            vehicle.setServiceYear(updatedVehicle.getServiceYear());
            vehicle.setOwnerName(updatedVehicle.getOwnerName());
            vehicle.setCost(updatedVehicle.getCost());
            vehicleRepository.save(vehicle);

            return ResponseEntity.ok("Vehicle with service ID " + id + " updated successfully.");
        } else {
            return ResponseEntity.status(404).body("Vehicle with service ID " + id + " not found.");
        }
    }
}

package com.example.vehicle_service.service.impl;
import com.example.vehicle_service.model.Vehicle;
import com.example.vehicle_service.repository.VehicleRepository;
import com.example.vehicle_service.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    // Save vehicle in database
    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
    // Get all vehicles from database
    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
    // Get vehicle using ID
    @Override
    public Vehicle getVehicleById(long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            return vehicle.get();
        } else {
            throw new RuntimeException("Vehicle not found with ID: " + id);
        }
    }
    // Update vehicle
    @Override
    public Vehicle updateVehicle(Vehicle vehicle, long id) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));

        existingVehicle.setVehicleType(vehicle.getVehicleType());
        existingVehicle.setServiceYear(vehicle.getServiceYear());
        existingVehicle.setOwnerName(vehicle.getOwnerName());
        existingVehicle.setCost(vehicle.getCost());

        vehicleRepository.save(existingVehicle);
        return existingVehicle;
    }
    // Delete vehicle by ID
    @Override
    public void deleteVehicle(long id) {
        vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));
        vehicleRepository.deleteById(id);
    }
    //Delete all vehicles by service year
    @Override
    public void deleteByServiceYear(int year) {
        vehicleRepository.deleteByServiceYear(year);
    }
    //Get all vehicles serviced in a specific year
    @Override
    public List<Vehicle> getVehiclesByYear(int year) {
        return vehicleRepository.findByServiceYear(year);
    }
    //Get only vehicle type by service ID
    @Override
    public String getVehicleTypeByServiceId(long id) {
        return vehicleRepository.findVehicleTypeByServiceId(id);
    }
}

package com.example.vehicle_service.service;
import com.example.vehicle_service.model.Vehicle;
import java.util.List;
public interface VehicleService {
    Vehicle saveVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(long id);
    Vehicle updateVehicle(Vehicle vehicle, long id);
    void deleteVehicle(long id);

    void deleteByServiceYear(int year);
    List<Vehicle> getVehiclesByYear(int year);
    String getVehicleTypeByServiceId(long id);
}


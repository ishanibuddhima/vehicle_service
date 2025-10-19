package com.example.vehicle_service.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;
    @Column(name = "vehicle_type",nullable = false)
    private String vehicleType;
    @Column(name = "service_year",nullable = false)
    private int serviceYear;
    @Column(name = "owner_name",nullable = false)
    private String ownerName;
    @Column(name = "cost_id",nullable = false)
    private double cost;


    public Long getServiceId() {
        return serviceId;
    }
    public void setServiceId(Long serviceId) {

        this.serviceId = serviceId;
    }

    public String getVehicleType() {

        return vehicleType;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getServiceYear() {
        return serviceYear;
    }
    public void setServiceYear(int serviceYear) {
        this.serviceYear = serviceYear;
    }

    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
}

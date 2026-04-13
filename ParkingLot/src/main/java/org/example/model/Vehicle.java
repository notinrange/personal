package org.example.model;

public abstract class Vehicle {
    public String licencePlate;
    public VehicleType type;

    Vehicle(String licencePlate,VehicleType type){
        this.licencePlate = licencePlate;
        this.type = type;
    }
}

package org.example.models;

import org.example.enums.VehicleType;

public class Truck extends Vehicle{
    public Truck(String plate){
        super(plate, VehicleType.LARGE);
    }
}

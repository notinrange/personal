package org.example.models;

import org.example.enums.VehicleType;

public class Car extends Vehicle {
    public Car(String plate){
        super(plate, VehicleType.COMPACT);
    }
}

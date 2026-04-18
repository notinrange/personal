package org.example.models;

import org.example.enums.VehicleType;

public class MotorCycle extends Vehicle {
    public MotorCycle(String plate){
        super(plate, VehicleType.MOTORCYCLE);
    }
}

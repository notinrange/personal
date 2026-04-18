package org.example.models;

import org.example.enums.VehicleType;

public abstract class Vehicle {
    protected String licencePlate ;
    protected VehicleType type;

    public Vehicle(String licencePlate,VehicleType type){
        this.licencePlate = licencePlate;
        this.type = type;
    }

    public String getLicencePlate(){return licencePlate;}
    public VehicleType getType(){return type;}

    @Override
    public String toString(){return type + "(" + licencePlate + "}" ;}

}

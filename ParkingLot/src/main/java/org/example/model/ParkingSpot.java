package org.example.model;

public class ParkingSpot {
    public String spotId;
    SpotType spotType;
    public boolean isOccupied;
    Vehicle parkedVehicle;

    ParkingSpot(String spotId,SpotType spotType){
        this.spotId = spotId;
        this.spotType = spotType;
        this.isOccupied = false;
    }

    boolean canFit(Vehicle v){
        if(isOccupied) return  false;
        if(v.type == VehicleType.BIKE && spotType == SpotType.SMALL) return  true;
        if(v.type == VehicleType.CAR && spotType == SpotType.MEDIUM) return  true;
        if(v.type == VehicleType.TRUCK && spotType == SpotType.LARGE) return  true;
        return  false;
    }

    public void parkVehicle(Vehicle v){
        this.parkedVehicle = v;
        this.isOccupied = true;
    }

    public void removeVehicle(){
        this.parkedVehicle = null;
        this.isOccupied = false;
    }
}


package org.example.models;

import org.example.enums.SpotStatus;
import org.example.enums.VehicleType;

public class ParkingSpot {
    private String spotId;
    private VehicleType spotType;
    private SpotStatus status;
    private Vehicle currenVehicle;

    public ParkingSpot(String spotId,VehicleType spotType){
        this.spotId = spotId;
        this.spotType = spotType;
        this.status = SpotStatus.AVAILABLE;
    }

    public boolean isAvailable(){ return status == SpotStatus.AVAILABLE;}

    public String getSpotId(){return spotId;}

    public Vehicle getCurrenVehicle() {
        return currenVehicle;
    }
    public VehicleType getSpotType(){return spotType;}


    public boolean canFit(Vehicle v){
        return isAvailable() && v.getType() == this.spotType;
    }

    public void park(Vehicle v){
        this.currenVehicle = v;
        this.status = SpotStatus.OCCUPIED;
    }

    public void free(){
        this.currenVehicle = null;
        this.status = SpotStatus.AVAILABLE;
    }

    @Override
    public String toString(){
        return spotId + "[" + spotType + "/" + status + "]";
    }
}

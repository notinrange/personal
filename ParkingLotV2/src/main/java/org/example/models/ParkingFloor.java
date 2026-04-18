package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private int floorNo;
    private List<ParkingSpot> spots;

    public ParkingFloor(int floorNo){
        this.floorNo = floorNo;
        this.spots = new ArrayList<>();
    }

    public void addSpot(ParkingSpot spot){
        spots.add(spot);
    }

    public ParkingSpot getAvailableSpot(Vehicle vehicle){
        for(ParkingSpot spot :spots){
            if(spot.canFit(vehicle)) return spot;
        }
        return null;
    }

    public int getFloorNo(){return floorNo;}

    public void printStatus(){
        System.out.println(" Floor " + floorNo + ":");
        spots.forEach(s-> System.out.println("   "+s));
    }
}

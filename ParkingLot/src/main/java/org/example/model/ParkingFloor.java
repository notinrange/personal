package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    public String floorId;
    public List<ParkingSpot> spots;

    public ParkingFloor(String floorId, int smallSpots, int mediumSpots, int largeSpots){
        this.floorId = floorId;
        this.spots = new ArrayList<>();

        for(int i = 0;i<smallSpots;i++){
            spots.add(new ParkingSpot(floorId + "-S" + i, SpotType.SMALL));
        }

        for(int i = 0;i<mediumSpots;i++){
            spots.add(new ParkingSpot(floorId + "-M" + i, SpotType.MEDIUM));
        }

        for(int i = 0;i<largeSpots;i++){
            spots.add(new ParkingSpot(floorId + "-L" + i, SpotType.LARGE));
        }
    }

    public ParkingSpot findAvailableSpot(Vehicle v){
        for(ParkingSpot spot: spots){
            if(spot.canFit(v)) return spot;
        }
        return  null;
    }
}

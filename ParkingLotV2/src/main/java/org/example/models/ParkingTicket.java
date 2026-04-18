package org.example.models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingTicket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingSpot spot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public ParkingTicket(Vehicle vehicle, ParkingSpot spot){
        this.ticketId = "T-"+ UUID.randomUUID().toString().substring(0,6).toUpperCase();
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
    }

    public String getTicketId(){ return ticketId;}

    public Vehicle getVehicle(){return vehicle;}
    public ParkingSpot getSpot(){return  spot;}
    public LocalDateTime getEntryTime(){return entryTime;}

    public void markExit(){
        this.exitTime = LocalDateTime.now();
    }

    public long getDurationMinutes(){
        if(exitTime == null) return 0;
        long mins = Duration.between(entryTime,exitTime).toMinutes();
        return mins == 0 ? 1: mins;
    }

    @Override
    public String toString(){
        return ticketId + " | " + vehicle + " | Spot: " + spot.getSpotId() + " | Entry: " + entryTime.toLocalTime();
    }
}

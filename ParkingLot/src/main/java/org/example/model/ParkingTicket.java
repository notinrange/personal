package org.example.model;

public class ParkingTicket {
    public String ticketId;
    public long exitTime;
    public Vehicle vehicle;
    public ParkingSpot spot;
    public long entryTime;
    public TicketStatus status;
    public double fare;

    public ParkingTicket(Vehicle v, ParkingSpot spot){
        this.ticketId = "TKT-" + System.currentTimeMillis();
        this.vehicle = v;
        this.spot = spot;
        this.entryTime = System.currentTimeMillis();
        this.status = TicketStatus.ACTIVE;
    }
}

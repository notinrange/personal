package org.example;

import org.example.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    String name;
    List<ParkingFloor> floors;

    Map<String, ParkingTicket> activeTickets;
    private static ParkingLot instance;

    private ParkingLot(String name){
        this.name = name;
        this.floors = new ArrayList<>();
        this.activeTickets = new HashMap<>();
    }

    public static ParkingLot getInstance(String name){
        if(instance == null){
            instance = new ParkingLot(name);
        }
        return  instance;
    }

    void addFloor(ParkingFloor floor){
        floors.add(floor);
    }

    ParkingTicket parkVehicle(Vehicle vehicle){
        for(ParkingFloor floor : floors){
            ParkingSpot spot = floor.findAvailableSpot(vehicle);

            if(spot != null){
                spot.parkVehicle(vehicle);
                ParkingTicket ticket = new ParkingTicket(vehicle,spot);
                activeTickets.put(ticket.ticketId,ticket);

                System.out.println("PARKED! Vehicle" + vehicle.licencePlate + " Ticket: " + ticket.ticketId + " | Spot: " + spot.spotId);
                return ticket;
            }
        }
        System.out.println(" PARKING Full");
        return  null;
    }

    double exitVehicle(String ticketId){
        ParkingTicket ticket = activeTickets.get(ticketId);

        if(ticket == null){
            System.out.println("Invalid Ticket!");
            return 0;
        }
        ticket.exitTime = System.currentTimeMillis();
        double fare = calculateFare(ticket);
        ticket.fare = fare;
        ticket.status = TicketStatus.PAID;

        ticket.spot.removeVehicle();
        activeTickets.remove(ticketId);
        System.out.println(" EXIT! Fare: ₹" + fare);
        return fare;
    }

    private double calculateFare(ParkingTicket t){
        long ms = t.exitTime - t.entryTime;
        long hours = Math.max(1, ms/3600000);

        double ratePerHour;
        switch (t.vehicle.type){
            case BIKE -> ratePerHour = 20;
            case CAR -> ratePerHour = 50;
            case TRUCK -> ratePerHour = 100;
            default -> ratePerHour = 50;
        }
        return hours * ratePerHour;
    }

    void displayStatus(){
        System.out.println("==============" + name + "==================");
        for(ParkingFloor floor : floors){
            long free = floor.spots.stream().filter(s->!s.isOccupied).count();
            System.out.println("Floor " + floor.floorId + ": " + free + " spots free");
        }
    }
}

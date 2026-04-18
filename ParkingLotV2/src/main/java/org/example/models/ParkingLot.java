package org.example.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private static ParkingLot instance;
    private String name;
    private List<ParkingFloor> floors;
    private Map<String, ParkingTicket> activeTickets;

    private ParkingLot(String name){
        this.name = name;
        this.floors = new ArrayList<>();
        this.activeTickets = new HashMap<>();
    }

    public static ParkingLot getInstance(String name){
        if(instance==null){
            instance = new ParkingLot(name);
        }
        return  instance;
    }

    public  void addFloor(ParkingFloor floor){
        floors.add(floor);
    }

    public ParkingTicket parkVehicle(Vehicle vehicle){
        if(activeTickets.containsKey(vehicle.getLicencePlate())){
            throw new RuntimeException(vehicle + " is already parked!");
        }

        for(ParkingFloor floor : floors){
            ParkingSpot spot = floor.getAvailableSpot(vehicle);
            if(spot != null){
                spot.park(vehicle);
                ParkingTicket ticket = new ParkingTicket(vehicle,spot);
                activeTickets.put(vehicle.getLicencePlate(),ticket);
                System.out.println("Parked: " + vehicle + " at Floor " + floor.getFloorNo() + " Spot "+ spot.getSpotId());
                System.out.println("Ticket: " + ticket);
                return ticket;
            }

        }

        throw  new RuntimeException("No available spot for " + vehicle.getType());
    }


    public  void exitVehicle(String licencePlate, PaymentStrategy payment){
        ParkingTicket ticket =  activeTickets.get(licencePlate);
        if(ticket==null){
            throw new RuntimeException("No active ticket for: " + licencePlate);
        }

        ticket.markExit();
        double fee = FeeCalculator.calculate(ticket);
        System.out.println("\n Exiting: " + ticket.getVehicle() + " | Duration: " + ticket.getDurationMinutes() + " min" + " | Fee: Rs." + String.format("%.2f", fee));
        payment.pay(fee);

        ticket.getSpot().free();
        activeTickets.remove(licencePlate);
    }

    public void printStatus(){
        System.out.println("\n----" + name + " Status----");
        floors.forEach(ParkingFloor::printStatus);
        System.out.println("Active tickets: " + activeTickets.size());
    }
}

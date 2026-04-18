package org.example.models;

import org.example.enums.VehicleType;

import java.util.Map;

public class FeeCalculator {

    private static  final Map<VehicleType,Double> RATES = Map.of(
            VehicleType.MOTORCYCLE,20.0,
            VehicleType.COMPACT,40.0,
            VehicleType.LARGE, 60.0
    );

    public static double calculate(ParkingTicket ticket){
        long minutes = ticket.getDurationMinutes();
        double hours = Math.ceil(minutes/60.0);
        if(hours<1) hours = 1;
        double rate = RATES.getOrDefault(ticket.getVehicle().getType(),40.0);
        return hours * rate;
    }
}

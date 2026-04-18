package org.example;

import org.example.enums.VehicleType;
import org.example.models.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        ParkingLot lot = ParkingLot.getInstance("DLF Cyber Hub Parking");
        ParkingFloor f1 = new ParkingFloor(1);
        f1.addSpot(new ParkingSpot("B1-01", VehicleType.MOTORCYCLE));
        f1.addSpot(new ParkingSpot("B1-02", VehicleType.MOTORCYCLE));
        f1.addSpot(new ParkingSpot("B1-03", VehicleType.COMPACT));
        f1.addSpot(new ParkingSpot("B1-04", VehicleType.COMPACT));
        f1.addSpot(new ParkingSpot("B1-05", VehicleType.LARGE));


        ParkingFloor f2 = new ParkingFloor(2);
        f2.addSpot(new ParkingSpot("B2-01", VehicleType.COMPACT));
        f2.addSpot(new ParkingSpot("B2-02", VehicleType.COMPACT));
        f2.addSpot(new ParkingSpot("B2-03", VehicleType.LARGE));

        lot.addFloor(f1);
        lot.addFloor(f2);

        lot.printStatus();
        System.out.println();

        Vehicle bike = new MotorCycle("DL-01-BIKE");
        Vehicle car1 = new Car("DL-02-CAR1");
        Vehicle car2 = new Car("DL-03-CAR2");
        Vehicle truck = new Truck("DL-04-TRK");

        lot.parkVehicle(bike);
        lot.parkVehicle(car1);
        lot.parkVehicle(car2);
        lot.parkVehicle(truck);
        System.out.println();

        lot.printStatus();
        System.out.println();

        try {
            lot.parkVehicle(car1);
        }catch (RuntimeException e){
            System.out.println("Expected: " + e.getMessage());
        }

        lot.exitVehicle("DL-02-CAR1", new CardPayment("4242"));
        lot.exitVehicle("DL-01-BIKE", new CashPayment());
        System.out.println();

        try{
            lot.exitVehicle("DL-99-FAKE", new CashPayment());
        }catch (RuntimeException e){
            System.out.println("Expected: "+ e.getMessage());
        }

        lot.printStatus();
    }
}

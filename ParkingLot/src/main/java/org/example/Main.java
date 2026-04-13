package org.example;

import org.example.model.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        ParkingLot lot = ParkingLot.getInstance(" Office Parking");

        ParkingFloor f1 = new ParkingFloor("F1",5,10,2);
        ParkingFloor f2 = new ParkingFloor("F2",5,10,2);

        lot.addFloor(f1);
        lot.addFloor(f2);

        Vehicle car1 = new Car("DL-01-AB-1234");
        Vehicle bike1 = new Bike("DL-01-AB-5678");
        Vehicle truck1 = new Truck("DL-01-AB-999");

        ParkingTicket t1 = lot.parkVehicle(car1);
        ParkingTicket t2 = lot.parkVehicle(bike1);
        ParkingTicket t3 = lot.parkVehicle(truck1);

        lot.displayStatus();

        lot.exitVehicle(t1.ticketId);
        lot.exitVehicle(t2.ticketId);
    }
}

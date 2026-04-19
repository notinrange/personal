package org.example;

import org.example.enums.Direction;

public class Main {
    static void main() {
        ElevatorController controller = new ElevatorController(new NearestElevatorStrategy());
        Elevator e1 = new Elevator("E1",1);
        Elevator e2 = new Elevator("E2",8);

        controller.addElevator(e1);
        controller.addElevator(e2);
        controller.printStatus();

        System.out.println("\n External Requests");
        controller.requestElevator(3, Direction.UP);
        controller.requestElevator(7,Direction.DOWN);
        controller.requestElevator(5,Direction.UP);


        System.out.println("\n External Requests");
        controller.requestFloor("E1",6);
        controller.requestFloor("E1",9);
        controller.requestFloor("E2",2);
        controller.printStatus();


        System.out.println("\n Processing all requests");
        controller.processAll();
        controller.printStatus();

    }
}

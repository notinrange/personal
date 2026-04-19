package org.example;

import org.example.enums.Direction;
import org.example.enums.RequestType;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private final List<Elevator> elevators;
    private final DispatchStrategy strategy;

    public ElevatorController(DispatchStrategy strategy){
        this.elevators = new ArrayList<>();
        this.strategy = strategy;
    }

    public  void addElevator(Elevator elevator){
        elevators.add(elevator);
    }

    public void requestElevator(int floor, Direction direction){
        Request req = new Request(floor, direction, RequestType.EXTERNAL);
        Elevator best = strategy.findBest(elevators,req);
        System.out.println("\nDispatching to " + best.getId()
                    + " for floor " + floor + " " + direction);
        best.addRequest(req);

    }

    public void requestFloor(String elevatorId,int floor){
        elevators.stream().filter(e->e.getId().equals(elevatorId))
                .findFirst()
                .ifPresent(e->{
                    Direction dir = floor > e.getCurrentFloor() ? Direction.UP : Direction.DOWN;
                    e.addRequest(new Request(floor,dir,RequestType.INTERNAL));
                });
    }

    public void processAll(){
        elevators.forEach(Elevator::processAllRequests);
    }

    public void printStatus(){
        System.out.println("\n--------Controller Status---------");
        elevators.forEach(System.out::println);
    }
}

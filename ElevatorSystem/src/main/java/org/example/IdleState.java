package org.example;

public class IdleState implements  ElevatorState{

    @Override
    public void handleRequest(Elevator elevator){
        if(!elevator.getUpQueue().isEmpty()){
            System.out.println("  [" + elevator.getId() + "] IDLE -> MOVING_UP");
            elevator.setState(new MovingUpState());
            elevator.getState().handleRequest(elevator);
        }else if(!elevator.getDownQueue().isEmpty()){
            System.out.println(" [" + elevator.getId() + " IDLE -> MOVING_DOWN");
            elevator.setState(new MovingDownState());
            elevator.getState().handleRequest(elevator);
        }

    }

    @Override
    public String getStateName(){return "IDLE";}
}

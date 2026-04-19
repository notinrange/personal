package org.example;

import java.util.TreeSet;

public class MovingDownState implements ElevatorState{
    @Override
    public void handleRequest(Elevator elevator){
        TreeSet<Integer> downQueue = elevator.getDownQueue();
        if(downQueue.isEmpty()){
            if(!elevator.getUpQueue().isEmpty()){
                System.out.println(" [" + elevator.getId() + "] DOWN done -> MOVING_UP");
                elevator.setState(new MovingUpState());
                elevator.getState().handleRequest(elevator);
            }else{
                System.out.println(" [" + elevator.getId() + "] DOWN done -> IDLE");
                elevator.setState(new IdleState());
            }
            return;
        }
        int nextFloor = downQueue.last();
        downQueue.remove(nextFloor);
        elevator.setCurrentFloor(nextFloor);
        System.out.println(" [" + elevator.getId() + "] ↓ arrived floor " + nextFloor);
        elevator.getDisplay().show(elevator);
    }

    @Override
    public String getStateName(){return "MOVING_DOWN";}
}

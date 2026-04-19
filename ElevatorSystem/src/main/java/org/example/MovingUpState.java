package org.example;

import java.util.TreeSet;

public class MovingUpState implements ElevatorState{

    @Override
    public void handleRequest(Elevator elevator){
        TreeSet<Integer> upQueue = elevator.getUpQueue();
        if(upQueue.isEmpty()){
            if(!elevator.getDownQueue().isEmpty()){
                System.out.println(" [" + elevator.getId() + "] UP done -> MOVING DOWN");
                elevator.setState(new MovingDownState());
                elevator.getState().handleRequest(elevator);
            }else{
                System.out.println(" [" + elevator.getId() + "] UP done -> IDLE");
                elevator.setState(new IdleState());
            }
            return;
        }

        int nextFloor  = upQueue.first();
        upQueue.remove(nextFloor);
        elevator.setCurrentFloor(nextFloor);
        System.out.println(" ["+elevator.getId() + "] ^ arrived Floor " + nextFloor);
        elevator.getDisplay().show(elevator);
    }

    @Override
    public String getStateName(){ return "MOVING_UP";}

}

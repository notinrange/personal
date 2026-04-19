package org.example;

public class Display {
    public void show(Elevator elevator){
        System.out.println(" [Display " + elevator.getId() + "] Floor: " + elevator.getCurrentFloor() + " | State:" + elevator.getState().getStateName());
    }
}

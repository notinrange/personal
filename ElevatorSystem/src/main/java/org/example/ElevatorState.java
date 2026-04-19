package org.example;

public interface ElevatorState {
    void handleRequest(Elevator elevator);
    String getStateName();
}

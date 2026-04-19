package org.example;

import org.example.enums.Direction;
import org.example.enums.RequestType;

import java.util.TreeSet;

public class Elevator {
    private String id;
    private int currentFloor;
    private ElevatorState state;
    private TreeSet<Integer> upQueue;
    private TreeSet<Integer> downQueue;
    private Display display;

    public Elevator(String id, int initialFloor){
        this.id = id;
        this.currentFloor = initialFloor;
        this.state = new IdleState();
        this.upQueue = new TreeSet<>();
        this.downQueue = new TreeSet<>();
        this.display = new Display();
    }

    public void addRequest(Request req){
        if(req.getDirection() == Direction.UP || (req.getType() == RequestType.INTERNAL) && req.getFloor() >currentFloor ){
            upQueue.add(req.getFloor());
        }else{
            downQueue.add(req.getFloor());
        }
        System.out.println("  [" + id + "] queued: " + req + " | upQ=" + upQueue + " downQ=" + downQueue);
    }

    public void step(){
        state.handleRequest(this);
    }

    public void processAllRequests(){
        int safetyLimit = 100;
        while((!upQueue.isEmpty() || !downQueue.isEmpty()) && safetyLimit-- >0){
            step();
        }
        if(upQueue.isEmpty() || downQueue.isEmpty() ){
            System.out.println(" [" + id + "] All request served. IDLE at floor " + currentFloor);
        }
    }

    public int estimateCost(int targetFloor){
        return Math.abs(currentFloor-targetFloor) + upQueue.size() + downQueue.size();
    }

    public String getId(){return id;}
    public int getCurrentFloor(){return currentFloor;}
    public void setCurrentFloor(int f){this.currentFloor = f;}
    public ElevatorState getState(){return state;}
    public void setState(ElevatorState s){this.state = s;}
    public TreeSet<Integer> getUpQueue(){return upQueue;}
    public TreeSet<Integer> getDownQueue(){return downQueue;}
    public Display getDisplay(){return display;}

    @Override
    public String toString(){
        return "Elevator[ " + id + " floor=" + currentFloor +
                " State = " + state.getStateName()+
                " upQ=" + upQueue + "downQ=" + downQueue + "]";
    }
}

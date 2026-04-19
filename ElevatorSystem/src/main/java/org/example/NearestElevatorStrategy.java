package org.example;

import java.util.List;

public class NearestElevatorStrategy implements DispatchStrategy{
    @Override
    public Elevator findBest(List<Elevator> elvators, Request request){
        Elevator best = null;
        int minCost = Integer.MAX_VALUE;
        for(Elevator e : elvators){
            int cost = e.estimateCost(request.getFloor());
            if(cost<minCost){
                minCost = cost;
                best = e;
            }
        }
        return best;
    }
}

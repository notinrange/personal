package org.example;

import java.util.List;

public interface DispatchStrategy {
    Elevator findBest(List<Elevator> elelvators, Request request);
}

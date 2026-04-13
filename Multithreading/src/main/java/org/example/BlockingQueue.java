package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    private Queue<Integer>q;
    private  int capacity;
    public BlockingQueue(int cap){
        q = new LinkedList<>();
        capacity = cap;
    }

    public boolean add(int item) throws InterruptedException {
        synchronized (q){
            if(q.size()==capacity){
                q.wait();
            }
            q.add(item);
            q.notifyAll();
            return true;
        }
    }

    public int remove() throws InterruptedException {
        synchronized (q){
            if(q.isEmpty()){
                q.wait();
            }
            int element = q.poll();
            q.notifyAll();
            return element;
        }
    }
}

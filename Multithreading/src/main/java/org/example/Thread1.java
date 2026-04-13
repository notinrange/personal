package org.example;

public class Thread1 extends  Thread{

    public Thread1(String threadName){
        super(threadName);
    }
    // Thread.currentThread() gives current instance of thread
    @Override
    public void run(){
        for(int i = 0;i<5;i++){
            System.out.println("Inside Thread 1 "+ Thread.currentThread().getName() + " " + i);
        }
    }
}

package org.example;

public class Stack {
    private int[] array;
    private  int stackTop;
    Object lock;

    public Stack(int capacity){
        array = new int[capacity];
        stackTop = -1;
        lock = new Object();
    }

//    public boolean push(int element)  {
//        synchronized (lock){
//            if(isFull()) return false;
//            ++ stackTop;
//            try {
//                Thread.sleep(1000);
//            }catch (Exception e){
//
//            }
//            array[stackTop] = element;
//            return true;
//        }
//    }

    public synchronized boolean push(int element)  {
            if(isFull()) return false;
            ++ stackTop;
            try {
                Thread.sleep(1000);
            }catch (Exception e){

            }
            array[stackTop] = element;
            return true;
    }

//    public  int pop() {
//        synchronized (lock){
//            if(isEmpty())return Integer.MIN_VALUE;
//            int obj = array[stackTop];
//            array[stackTop] = Integer.MAX_VALUE;
//            try {
//                Thread.sleep(1000);
//            }catch (Exception e){
//
//            }
//            stackTop--;
//            return obj;
//        }
//    }

    public synchronized int pop() {
            if(isEmpty())return Integer.MIN_VALUE;
            int obj = array[stackTop];
            array[stackTop] = Integer.MAX_VALUE;

            // sleep method for avoiding state collapsion
            try {
                Thread.sleep(1000);
            }catch (Exception e){

            }
            stackTop--;
            return obj;
    }

    // either i can make piece of code sync or method sync in that case this keyword in lock
    public  boolean isEmpty(){
        return  stackTop<0;
    }

    public boolean isFull(){
        return stackTop >= array.length-1;
    }


}

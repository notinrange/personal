package org.example;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)  {
        System.out.println("Main started");
        // Main and thread1 are user Thread.
        // program terminated after executing user thread but not for daemon thread.
        // for making thread1 daemon u should make thread1.setDaemon(true) for thread1.
        // you can set thread name by overwriting the constructor of thread1.
        Thread thread1 = new Thread1("MyThread1");
        thread1.start();

        Thread thread2 = new Thread(new Thread2(),"MyThread2");
        thread2.start();

        // Why we are using Runnable in Thread ?
        // Because I want to extend my child Thread from  Runnable it is possible by Multiple Interface which is allowed in java.
        // whereas, thread1 use Multiple Inheritance to create child thread which is not allowed in java.

        // I can create Runnable directly like this
        Thread thread3 = new Thread(()->{
            for(int i = 0;i<5;i++){
                System.out.println(Thread.currentThread() + ", "+ i);
            }
        },"MyThread3");
        thread3.start();

        Stack st = new Stack(5);
        new Thread(()->{
            int counter = 0;
            while(++counter<10){
                System.out.println("Pushed : "+ st.push(100));
            }
        },"Pusher").start();

        new Thread(()->{
            int counter = 0;
            while(++counter<10){
                System.out.println("Popped : "+ st.pop());
            }
        },"Popper").start();

        // Synchronization without sync race-condition occurs




        Thread thread4 = new Thread(()->{
            try{
                Thread.sleep(1);
                for(int i = 1000;i>0;i--);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"States");

        thread4.start();

        while (true){
            Thread.State state = thread4.getState();
            System.out.println(state);
            if(state==Thread.State.TERMINATED){
                break;
            }
        }


        // Use of Thread Join-> it allows to execute child thread first then main thread executes
        Thread thread5 = new Thread(()->{
            System.out.println(Thread.currentThread());
        },"Our Thread");

        thread5.start();

        try {
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread5.setPriority(1);//this thread executes first
        System.out.println("Main ended");


        // Deadlock condition
        String lock1 = "rahul";
        String lock2 = "kumar";
        Thread thread6 = new Thread(()->{
            synchronized (lock1){
                try{
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("Lock acquired");
                }
            }
        },"thread6");


        Thread thread7 = new Thread(()->{
            synchronized (lock2){
                try{
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("Lock acquired");
                }
            }
        },"thread7");

        thread6.start();
        thread7.start();

    }
}
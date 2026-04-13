package org.example;

public class StaticMethodSync {

    // Volatile Keyword stored in ram to avoid race condition
    private  static volatile StaticMethodSync instance = null;
    private StaticMethodSync(){
        System.out.println("Static Method instantiated");
    }

    // Singleton Design Pattern
    private static StaticMethodSync getInstance(){
        if(instance==null){
            synchronized (StaticMethodSync.class){
                if(instance==null){
                    instance = new StaticMethodSync();
                }
            }
        }
        return instance;
    }

}

package org.example;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String,Product> products; // code->product
    private Map<String,Integer> stock; // code-> quantity

    public Inventory(){
        products = new HashMap<>();
        stock = new HashMap<>();
    }

    public void addProduct(Product p, int qty){
        products.put(p.getCode(),p);
        stock.put(p.getCode(), stock.getOrDefault(p.getCode(),0) + qty);
        System.out.println("Stocked: "+ p + " x" + qty);
    }

    public Product getProduct(String code){
        return products.get(code);
    }

    public  boolean isAvailable(String code){
        return stock.getOrDefault(code,0)>0;
    }

    public  void deduct(String code){
        stock.put(code, stock.get(code) - 1);
    }

    public  boolean hasAnyStock(){
        return stock.values().stream().anyMatch(q ->q >0);
    }

    public  void printStock(){
        System.out.println(" Inventory:");
        products.forEach((code,p)->{
            System.out.println("    " + p + " | qty=" + stock.get(code));
        });
    }



}

package org.example;

public class OutOfStockState implements VendingMachineState{

    @Override
    public void insertCoin(VendingMachine vm, double amount){
        System.out.println(" Machine is out of stock. Returning Rs." + amount);
    }

    @Override
    public void selectProduct(VendingMachine vm,String code){
        System.out.println(" Machine is out of stock");
    }

    @Override
    public  void dispense(VendingMachine vm){
        System.out.println(" Nothing to dispense - machine is empty.");
    }

    @Override public void refund(VendingMachine vm){
        System.out.println("Nothing inserted");
    }
    @Override public void cancel(VendingMachine vm){
        System.out.println(" Nothing to cancel.");
    }

    @Override public String getStateName(){return "OUT_OF_STOCK";}
}

package org.example;

public interface VendingMachineState {
    void insertCoin(VendingMachine vm, double amount);
    void selectProduct(VendingMachine vm,String code);
    void dispense(VendingMachine vm);
    void refund(VendingMachine vm);
    void cancel(VendingMachine vm);
    String getStateName();
}

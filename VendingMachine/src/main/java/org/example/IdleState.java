package org.example;

public class IdleState implements VendingMachineState{

    @Override
    public void insertCoin(VendingMachine vm, double amount){
        vm.addMoney(amount);
        System.out.println(" Inserted Rs. "+ amount + " | Total: Rs. " + vm.getInsertedMoney());
        vm.setState(new HasMoneyState());
    }

    @Override public void selectProduct(VendingMachine vm, String code){
        System.out.println(" Please insert money first");
    }

    @Override public void dispense(VendingMachine vm){
        System.out.println(" Insert money and select a product first.");
    }

    @Override public void refund(VendingMachine vm){
        System.out.println(" Nothing to cancel");
    }

    @Override public  void cancel(VendingMachine vm){
        System.out.println(" Nothing to cancel");
    }

    @Override public String getStateName(){return "IDLE";}
}

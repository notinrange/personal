package org.example;

public class DispensingState implements VendingMachineState{

    @Override public void insertCoin(VendingMachine vm, double amount){
        System.out.println(" Dispensing in progress, please wait.");
    }

    @Override public void selectProduct(VendingMachine vm, String code) {
        System.out.println("  Dispensing in progress, please wait.");
    }
    @Override public void cancel(VendingMachine vm) {
        System.out.println("  Cannot cancel — dispensing already started.");
    }
    @Override public void refund(VendingMachine vm) {
        System.out.println("  Cannot refund — dispensing already started.");
    }

    @Override
    public void dispense(VendingMachine vm){
        Product p = vm.getSelectedProduct();
        double change = vm.getInsertedMoney() - p.getPrice();

        vm.getInventory().deduct(p.getCode());
        System.out.println(" Dispensing: " + p.getName());

        if(change >0){
            System.out.println("Change returned: Rs." + String.format("%.2f",change));
        }

        vm.setInsertedMoney(0);
        vm.setSelectedProduct(null);

        if(!vm.getInventory().hasAnyStock()){
            System.out.println(" Machine is now out of Stock.");
            vm.setState(new OutOfStockState());
        }else{
            vm.setState(new IdleState());
        }
    }

    @Override
    public String getStateName(){ return "DISPENSING";}
}

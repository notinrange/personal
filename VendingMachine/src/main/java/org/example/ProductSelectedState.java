package org.example;

public class ProductSelectedState implements VendingMachineState{
    @Override public  void insertCoin(VendingMachine vm, double amount){
        System.out.println(" Product already selected. Press dispense or cancel");
    }

    @Override public void selectProduct(VendingMachine vm, String code){
        System.out.println("Already selected: " + vm.getSelectedProduct().getName() + ". Cancel first to change.");
    }

    @Override
    public void dispense(VendingMachine vm){
        vm.setState(new DispensingState());
        vm.getState().dispense(vm);
    }

    @Override
    public void cancel(VendingMachine vm){
        System.out.println(" Cancelled. Refunding Rs." + vm.getInsertedMoney());
        vm.setSelectedProduct(null);
        vm.refundMoney();
        vm.setState(new IdleState());
    }

    @Override public  void refund(VendingMachine vm){ cancel(vm);}

    @Override public String getStateName(){ return "PRODUCT_SELECTED";}
}

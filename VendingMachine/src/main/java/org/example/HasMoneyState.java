package org.example;

public class HasMoneyState implements VendingMachineState{

    @Override
    public void insertCoin(VendingMachine vm, double amount){
        vm.addMoney(amount);
        System.out.println(" Added Rs." + amount + " | Total: Rs." + vm.getInsertedMoney());
    }

    @Override
    public void selectProduct(VendingMachine vm,String code){
        Product p = vm.getInventory().getProduct(code);
        //vending machine mein product hi nahi h uske code ka according
        if(p==null){
            System.out.println(" Invalid product code: "+code);
            return;
        }

        // vending machine inventory mein product ka count  0 ho gya h
        if(!vm.getInventory().isAvailable(code)){
            System.out.println(" "+ p.getName() + " is out of stock. Refunding Rs." + vm.getInsertedMoney());
            vm.refundMoney();
            vm.setState(new IdleState());
            return;
        }

        // vending machine mein paise kam daale h
        if(vm.getInsertedMoney() < p.getPrice()){
            System.out.println(" Insufficient funds. Need Rs. " + p.getPrice() + ", have Rs." + vm.getInsertedMoney());
            return;
        }

        vm.setSelectedProduct(p);
        System.out.println(" Selected: " + p);
        vm.setState(new ProductSelectedState());
    }

    @Override public void dispense(VendingMachine vm){
        System.out.println(" Select a product first");
    }

    @Override
    public void refund(VendingMachine vm){
        System.out.println(" Refunding Rs. " + vm.getInsertedMoney());
        vm.refundMoney();
        vm.setState(new IdleState());
    }

    @Override
    public void cancel(VendingMachine vm){
        refund(vm);
    }
    @Override public String getStateName() { return "HAS_MONEY";}
}

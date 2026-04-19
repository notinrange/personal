package org.example;

public class VendingMachine {
    private static VendingMachine instance;

    private VendingMachineState state;
    private Inventory inventory;
    private double insertedMoney;
    private Product selectedProduct;

    private VendingMachine(){
        inventory = new Inventory();
        state = new IdleState();
        insertedMoney = 0;
    }

    static VendingMachine getInstance(){
        if(instance == null) instance = new VendingMachine();
        return instance;
    }

    public void insertCoin(double amount){state.insertCoin(this,amount);}
    public void selectProduct(String code){ state.selectProduct(this,code);}
    public void dispense() { state.dispense(this);}
    public void refund() { state.refund(this);}
    public void cancel(){state.cancel(this);}

    public void restock(Product p,int qty){
        inventory.addProduct(p,qty);
        if(state instanceof OutOfStockState){
            System.out.println(" Restocked - returning to IDLE");
            state = new IdleState();
        }
    }

    public void addMoney(double amount){insertedMoney += amount;}
    public void refundMoney(){ insertedMoney = 0;}

    public VendingMachineState getState(){ return state;}
    public void setState(VendingMachineState s){ this.state = s;}
    public Inventory getInventory() { return inventory;}
    public double getInsertedMoney(){ return insertedMoney;}
    public void setInsertedMoney(double m){ this.insertedMoney = m;}
    public Product getSelectedProduct(){ return  selectedProduct;}
    public void setSelectedProduct(Product p){ this.selectedProduct = p;}

    public void printStatus(){
        System.out.println("\n---- Machine Status-----");
        System.out.println(" State: " + state.getStateName() );
        System.out.println(" Money: Rs." + insertedMoney);
        System.out.println(" Selected: " + (selectedProduct != null ? selectedProduct.getName() : "none"));
        inventory.printStock();
    }

}

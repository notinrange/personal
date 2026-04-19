package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        VendingMachine vm = VendingMachine.getInstance();

        Product chips = new Product("A1","Lays Chips",20.0);
        Product water = new Product("B1","Water 500ml",15.0);
        Product coke = new Product("C1","Coca Cola", 30.0);

        vm.restock(chips,2);
        vm.restock(water,1);
        vm.restock(coke,1);

        vm.printStatus();

        System.out.println("\n=== Happy path: buy chips===");
        vm.insertCoin(20);
        vm.selectProduct("A1");
        vm.dispense();
        vm.printStatus();

    }
}

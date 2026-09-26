package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
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

        System.out.println("\n===Buy Coke, insert excess money===");
        vm.insertCoin(20);
        vm.insertCoin(20);
        vm.selectProduct("C1");
        vm.dispense();

        System.out.println("\n ===Insufficient funds===");
        vm.insertCoin(10);
        vm.selectProduct("A1");
        vm.dispense();


        // ── Try operations in wrong state ──
        System.out.println("\n=== Invalid ops in IDLE ===");
        vm.dispense();
        vm.selectProduct("A1");

        // ── Drain stock → out of stock ──
        System.out.println("\n=== Last item → triggers OUT_OF_STOCK ===");
        vm.insertCoin(20);
        vm.selectProduct("A1");  // last chips
        vm.dispense();           // machine becomes empty
        vm.printStatus();

        // Try to buy when out of stock
        vm.insertCoin(20);       // should be rejected

        // ── Restock ──
        System.out.println("\n=== Restock ===");
        vm.restock(new Product("A1", "Lays Chips", 20.0), 3);
        vm.printStatus();



    }
}
// mvn compile
// mvn clean compile

// insided package -> java -cp target\classes org.example.Main

//  git clone git@github-personal:notinrange/personal.git
// ssh -T git@github-personal


// For repositories already cloned, configure the account once:
// git remote set-url origin git@github-personal:notinrange/personal.git


//   git remote -v
//   git config user.name "notinrange"
//   git config user.email "rahulkumarpoddar0703@gmail.com"
//   ssh -T git@github-personal

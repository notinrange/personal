package org.example;

import org.example.enums.TransactionType;

public class ATMMachine {
 
    public static void main(String[] args) {
 
        // Setup ATM with Rs 50,000 cash
        ATM atm = new ATM(50000);
 
        // Add accounts
        Account acc1 = new Account("ACC001", "Rahul Kumar", 15000);
        Account acc2 = new Account("ACC002", "Priya Sharma", 8000);
        atm.addAccount(acc1);
        atm.addAccount(acc2);
 
        // Cards
        Card card1 = new Card("4111-1111-1111-1111", "ACC001", 1234);
        Card card2 = new Card("4222-2222-2222-2222", "ACC002", 5678);
 
        System.out.println("========== ATM DEMO ==========\n");
 
        // ---- Scenario 1: Successful Withdrawal ----
        System.out.println("--- Scenario 1: Withdrawal ---");
        atm.insertCard(card1);
        atm.enterPin(1234);
        atm.selectTransaction(TransactionType.WITHDRAWAL);
        atm.dispatchCash(5000);
        atm.ejectCard();
 
        System.out.println();
 
        // ---- Scenario 2: Wrong PIN ----
        System.out.println("--- Scenario 2: Wrong PIN ---");
        atm.insertCard(card1);
        atm.enterPin(9999);  // wrong pin
        atm.ejectCard();
 
        System.out.println();
 
        // ---- Scenario 3: Check Balance ----
        System.out.println("--- Scenario 3: Check Balance ---");
        atm.insertCard(card2);
        atm.enterPin(5678);
        atm.selectTransaction(TransactionType.CHECK_BALANCE);
        atm.dispatchCash(0);
        atm.ejectCard();
 
        System.out.println();
 
        // ---- Scenario 4: Insufficient Balance ----
        System.out.println("--- Scenario 4: Insufficient Balance ---");
        atm.insertCard(card2);
        atm.enterPin(5678);
        atm.selectTransaction(TransactionType.WITHDRAWAL);
        atm.dispatchCash(20000); // more than balance
        atm.ejectCard();
 
        System.out.println("\n========== END ==========");
    }
}

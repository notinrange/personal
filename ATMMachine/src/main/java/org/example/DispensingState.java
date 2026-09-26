package org.example;

import org.example.enums.TransactionType;

class DispensingState implements ATMState {
 
    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("Please wait, dispensing cash...");
    }
 
    @Override
    public void ejectCard(ATM atm) {
        System.out.println("Please wait, dispensing cash...");
    }
 
    @Override
    public void enterPin(ATM atm, int pin) {
        System.out.println("Please wait, dispensing cash...");
    }
 
    @Override
    public void selectTransaction(ATM atm, TransactionType type) {
        System.out.println("Please wait, dispensing cash...");
    }
 
    @Override
    public void dispatchCash(ATM atm, double amount) {
        System.out.println("Please wait, dispensing cash...");
    }
}

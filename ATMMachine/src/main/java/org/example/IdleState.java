package org.example;

import org.example.enums.TransactionType;

class IdleState implements ATMState{

    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("Card inserted: " + card.getCardNumber());
        atm.setCurrentCard(card);
        atm.setState(atm.getCardInsertedState());
        System.out.println("Card inserted. Please enter your PIN.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("No card to eject.");
    }

    @Override
    public void enterPin(ATM atm, int pin) {
        System.out.println("Please insert a card first.");
    }

    @Override
    public void selectTransaction(ATM atm, TransactionType transactionType) {
        System.out.println("Please insert a card first.");
    }

    @Override
    public void dispatchCash(ATM atm, double amount) {
        System.out.println("Please insert a card first.");
    }
}

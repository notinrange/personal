package org.example;

import org.example.enums.TransactionType;

class AuthenticatedState implements ATMState{
    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("Card already active.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("Session ended. Card ejected.");
        atm.setCurrentCard(null);
        atm.setCurrentAccount(null);
        atm.setCurrentTransactionType(null);
        atm.setState(atm.getIdleState());
    }

    @Override
    public void enterPin(ATM atm, int pin) {
        System.out.println("Already authenticated.");
    }

    @Override
    public void selectTransaction(ATM atm, TransactionType type) {
        if (type == null) {
            System.out.println("Please select a valid transaction type.");
            return;
        }
        System.out.println("Transaction selected: " + type);
        atm.setCurrentTransactionType(type);
        atm.setState(atm.getTransactionSelectedState());
    }

    @Override
    public void dispatchCash(ATM atm, double amount) {
        System.out.println("Please select transaction type first.");
    }
}

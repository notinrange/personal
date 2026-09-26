package org.example;

import org.example.enums.TransactionType;

interface ATMState{
    void insertCard(ATM atm, Card card);
    void ejectCard(ATM atm);
    void enterPin(ATM atm, int pin);
    void selectTransaction(ATM atm, TransactionType transactionType);
    void dispatchCash(ATM atm, double amount);
}

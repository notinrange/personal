package org.example;

import org.example.enums.TransactionType;

class TransactionSelectedState implements ATMState{

    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("Transaction in progress.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("Transaction cancelled. Card ejected.");
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
        System.out.println("Transaction already selected.");
    }


    @Override
    public void dispatchCash(ATM atm, double amount){
        TransactionType type = atm.getCurrentTransactionType();
        Account account = atm.getCurrentAccount();
        CashDispenser dispenser = atm.getCashDispenser();

        if (type == TransactionType.CHECK_BALANCE) {
            System.out.println("Current balance: Rs" + account.getBalance());
            atm.setCurrentTransactionType(null);
            atm.setState(atm.getAuthenticatedState());
            return;
        }

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            atm.setCurrentTransactionType(null);
            atm.setState(atm.getAuthenticatedState());
            return;
        }

        if(type == TransactionType.WITHDRAWAL){
            if(!dispenser.hasSufficientCash(amount)){
                System.out.println("ATM does not have sufficient cash.");
                atm.setCurrentTransactionType(null);
                atm.setState(atm.getAuthenticatedState());
                return;
            }

             if (account.debit(amount)) {
                atm.setState(atm.getDispensingState());
                dispenser.dispenseCash(amount);
                System.out.println("Remaining balance: Rs" + account.getBalance());
                atm.setCurrentTransactionType(null);
                atm.setState(atm.getAuthenticatedState());
            } else {
                System.out.println("Insufficient account balance. Available: Rs" + account.getBalance());
                atm.setCurrentTransactionType(null);
                atm.setState(atm.getAuthenticatedState());
            }
        }
        else if (type == TransactionType.DEPOSIT) {
            account.credit(amount);
            System.out.println("Rs" + amount + " deposited successfully.");
            System.out.println("New balance: Rs" + account.getBalance());
            atm.setCurrentTransactionType(null);
            atm.setState(atm.getAuthenticatedState());
        } else {
            System.out.println("Unsupported transaction type: " + type);
            atm.setCurrentTransactionType(null);
            atm.setState(atm.getAuthenticatedState());
        }
    }

}

package org.example;

import org.example.enums.TransactionType;

class CardInsertedState implements ATMState {


    @Override
    public void insertCard(ATM atm, Card card) {
        System.out.println("Card is already inserted.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("Ejecting card...");
        atm.setCurrentCard(null);
        atm.setCurrentAccount(null);
        atm.setCurrentTransactionType(null);
        atm.setState(atm.getIdleState());
    }

    @Override
    public void enterPin(ATM atm, int pin) {
       Card card = atm.getCurrentCard();
       Account account = atm.getAccount(card.getAccountId());
       if(account != null && card.getPin()==pin){
             System.out.println("PIN verified. Welcome, " + account.getHolderName() + "!");
             atm.setCurrentAccount(account);
             atm.setState(atm.getAuthenticatedState());
       }else{
            System.out.println("Incorrect PIN. Please try again.");
       }
    }

    @Override
    public void selectTransaction(ATM atm, TransactionType transactionType) {
        System.out.println("Please enter PIN first.");
    }
 
    @Override
    public void dispatchCash(ATM atm, double amount) {
        System.out.println("Please enter PIN first.");
    }
}

package org.example;

import org.example.enums.TransactionType;

import java.util.HashMap;
import java.util.Map;

class ATM {
    // States
    private ATMState idleState;
    private ATMState cardInsertedState;
    private ATMState authenticatedState;
    private ATMState transactionSelectedState;
    private ATMState dispensingState;
 
    // Current state
    private ATMState currentState;
 
    // Current session data
    private Card currentCard;
    private Account currentAccount;
    private TransactionType currentTransactionType;
 
    // Dependencies
    private final CashDispenser cashDispenser;
    private final Map<String, Account> accounts; // accountId -> Account
 
    public ATM(double initialCash) {
        // Initialize states
        this.idleState = new IdleState();
        this.cardInsertedState = new CardInsertedState();
        this.authenticatedState = new AuthenticatedState();
        this.transactionSelectedState = new TransactionSelectedState();
        this.dispensingState = new DispensingState();
 
        // Start in idle state
        this.currentState = idleState;
 
        // Initialize dependencies
        this.cashDispenser = new CashDispenser(initialCash);
        this.accounts = new HashMap<>();
    }
 
    // ---- Account Management ----
    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }
 
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }
 
    // ---- Delegate to current state ----
    public void insertCard(Card card) { currentState.insertCard(this, card); }
    public void ejectCard() { currentState.ejectCard(this); }
    public void enterPin(int pin) { currentState.enterPin(this, pin); }
    public void selectTransaction(TransactionType type) { currentState.selectTransaction(this, type); }
    public void dispatchCash(double amount) { currentState.dispatchCash(this, amount); }
 
    // ---- Getters / Setters ----
    public ATMState getIdleState() { return idleState; }
    public ATMState getCardInsertedState() { return cardInsertedState; }
    public ATMState getAuthenticatedState() { return authenticatedState; }
    public ATMState getTransactionSelectedState() { return transactionSelectedState; }
    public ATMState getDispensingState() { return dispensingState; }
 
    public void setState(ATMState state) { this.currentState = state; }
 
    public Card getCurrentCard() { return currentCard; }
    public void setCurrentCard(Card card) { this.currentCard = card; }
 
    public Account getCurrentAccount() { return currentAccount; }
    public void setCurrentAccount(Account account) { this.currentAccount = account; }
 
    public TransactionType getCurrentTransactionType() { return currentTransactionType; }
    public void setCurrentTransactionType(TransactionType type) { this.currentTransactionType = type; }
 
    public CashDispenser getCashDispenser() { return cashDispenser; }
}

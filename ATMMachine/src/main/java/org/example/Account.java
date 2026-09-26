package org.example;

import org.example.enums.TransactionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Account{
    private String accountId;
    private String holderName;
    private double balance;
    private List<Transaction> transactions;


    public Account(String accountId, String holderName, double balance) {
        this.accountId = accountId;
        this.holderName = holderName;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountId() {
        return accountId;
    }
    public String getHolderName() {
        return holderName;
    }
    public double getBalance() {
        return balance;
    }

    public boolean debit(double amount){
        if(amount <= balance){
            balance -= amount;
            transactions.add(new Transaction(TransactionType.WITHDRAWAL, amount));
            return true;
        }
        return false;
    }

    public void credit(double amount){
        balance += amount;
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount));
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}

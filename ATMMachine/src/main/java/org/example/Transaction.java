package org.example;

import org.example.enums.TransactionType;

class Transaction{
    private String transactionId;
    private TransactionType transactionType;
    private double amount;
    private long timestamp;

    public Transaction(TransactionType transactionType, double amount) {
        this.transactionId = "TXN" + System.currentTimeMillis();
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = System.currentTimeMillis();
    }

    public String getTransactionId() {
        return transactionId;
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}

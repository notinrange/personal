package org.example;

class CashDispenser {
    private double totalCash;

    public CashDispenser(double totalCash) {
        if (totalCash < 0) {
            throw new IllegalArgumentException("Initial cash cannot be negative.");
        }
        this.totalCash = totalCash;
    }

    public boolean hasSufficientCash(double amount) {
        return amount > 0 && totalCash >= amount;
    }

    public void dispenseCash(double amount) {
        if (!hasSufficientCash(amount)) {
            throw new IllegalArgumentException("Invalid amount or insufficient ATM cash.");
        }
        totalCash -= amount;
        System.out.println("Dispensed cash: Rs" + amount);
        System.out.println("Remaining cash in dispenser: Rs" + totalCash);
    }

    public double getTotalCash() {
        return totalCash;
    }
}

package org.example;

class Card{
    private String cardNumber;
    private String accountId;
    private int pin;


    public Card(String cardNumber, String accountId, int pin) {
        this.cardNumber = cardNumber;
        this.accountId = accountId;
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getPin() {
        return pin;
    }
}

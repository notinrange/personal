package org.example.models;

public class CardPayment implements PaymentStrategy{
    private String cardLast4;

    public CardPayment(String cardLast4){
        this.cardLast4 = cardLast4;
    }

    @Override
    public void pay(double amount){
        System.out.println("Paid Rs."+String.format("%.2f",amount) + " via Card ending " + cardLast4);
    }
}

package org.example.models;

public class CashPayment implements PaymentStrategy{
    @Override
    public void pay(double amount){
        System.out.println("Paid Rs."+String.format("%.2f",amount) + "via Cash");
    }
}

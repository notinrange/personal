package org.example.model;

public class Bid {
    public String memberId;
    public int amount;
    long timestamp;

    public Bid(String memberId,int amount,long timestamp){
        this.memberId = memberId;
        this.amount = amount;
        this.timestamp = timestamp;
    }
}

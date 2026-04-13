package org.example.model;

public class Winner {
    String eventId;
    String winnerName;
    public int lowestBid;
    String date;

    public Winner(String eventId, String winnerName, int lowestBid,String date){
        this.eventId = eventId;
        this.winnerName = winnerName;
        this.lowestBid = lowestBid;
        this.date = date;
    }

    @Override
    public String toString(){
        return "{eventId=" + eventId + ", winner=" + winnerName +
                ", lowestBid=" + lowestBid + ", date=" + date + "}";
    }
}

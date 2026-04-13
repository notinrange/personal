package org.example.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Event {
    String eventId;
    public String date;
    public boolean closed;

    public Map<String, List<Bid>> memberBids;

    public Event(String eventId,String date){
        this.eventId = eventId;
        this.date = date;
        this.closed = false;
        this.memberBids = new LinkedHashMap<>();
    }
}

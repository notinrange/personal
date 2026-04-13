package org.example.service;

import org.example.model.Bid;
import org.example.model.Event;
import org.example.model.Member;
import org.example.model.Winner;

import java.util.*;

public class BidBlitzService {
    private Map<String, Member> members = new HashMap<>();
    private Map<String, Event> events = new HashMap<>();

    private List<Winner> winners = new ArrayList<>();

    private  long timeStampCounter = 0;

    public void addMember(String memberId,int superCoins){
        if(members.containsKey(memberId)){
            System.out.println("ERROR: Member" + memberId + "already exisits.");
            return;
        }
        members.put(memberId,new Member(memberId,superCoins));
        System.out.println("Member " + memberId + " added with " + superCoins + " super coins.");
    }

    public void createEvent(String eventId,String date){
        if(events.containsKey(eventId)){
            System.out.println("ERROR: Event " + eventId + " already exisits.");
            return;
        }
        events.put(eventId, new Event(eventId,date));
        System.out.println("Event " + eventId + " created for date " + date);
    }


    // --- Submit Bids ----
    // Rules :
    // 1. Max 5 bids at once
    // 2. Member must have >= max(bids) super coins
    // 3. Only max bid deducted from wallet
    // 4. Each bid must be unique for the member in that event
    // 5. Each bid > 0

    public void submitBids(String memberId, String eventId, List<Integer> bidAmounts){
        if(!members.containsKey(memberId)){
            System.out.println("ERROR: Member " + memberId + " not found. ");
            return;
        }
        if(!events.containsKey(eventId)){
            System.out.println("ERROR: Event " + eventId + " not found.");
        }

        Member member = members.get(memberId);
        Event event = events.get(eventId);

        if(event.closed){
            System.out.println("ERROR: Event " + eventId + " is already closed.");
            return;
        }
        if(bidAmounts.size() > 5){
            System.out.println("ERROR: Cannot submit more than 5 bids at once.");
            return;
        }
        for(int bid : bidAmounts){
            if(bid<=0){
                System.out.println("ERROR: All bids must be greater than zero.");
                return;
            }
        }

        Set<Integer> uniqueCheck = new HashSet<>(bidAmounts);

        if(uniqueCheck.size() != bidAmounts.size()){
            System.out.println("ERROR: All bids in a single submission must be unique");
            return;
        }

        List<Bid> existingBids = event.memberBids.getOrDefault(memberId,new ArrayList<>());
        Set<Integer> existingAmounts = new HashSet<>();
        for(Bid b : existingBids){
            existingAmounts.add(b.amount);
        }

        for(int bid : bidAmounts){
            if(existingAmounts.contains(bid)){
                System.out.println("ERROR: Bid "+ bid + " already submitted by " + memberId + " for event " + eventId);
                return;
            }
        }


        // Validations over now actual logic starts

        int maxBid = Collections.max(bidAmounts);

        if(member.superCoins< maxBid){
            System.out.println("ERROR: " + memberId + "doesn't have enough super coins. "+ "Required: " + maxBid + ", Available: "+ member.superCoins);
            return;
        }

        member.superCoins -= maxBid;

        List<Bid> newBids = new ArrayList<>();
        for(int amount : bidAmounts){
            newBids.add(new Bid(memberId, amount, timeStampCounter++));
        }

        event.memberBids.computeIfAbsent(memberId,k-> new ArrayList<>()).addAll(newBids);

        System.out.println("Bids submitted successfully for "+ memberId + " in event " + eventId + ". Deducted "+ maxBid + " super coins. " + "Remaining: " + member.superCoins);

    }

    // -- DECLARE WINNER ---
    // Winner = number with the LOWEST UNIQUE bid
    // if tie (same lowest bid by multiple members) -> member who submitted first wins
    public void declareWinner(String eventId){
        if(!events.containsKey(eventId)){
            System.out.println("ERROR: Event " + eventId + " not found. ");
            return;
        }

        Event event = events.get(eventId);

        if(event.closed){
            System.out.println("ERROR: Winner already declared for event " + eventId);
            return;
        }
        event.closed = true;

        Map<Integer, List<Bid>> bidMap = new TreeMap<>();
        for(Map.Entry<String, List<Bid>> entry : event.memberBids.entrySet()){
            for(Bid bid : entry.getValue()){
                bidMap.computeIfAbsent(bid.amount, k->new ArrayList<>()).add(bid);
            }
        }

        Bid winningBid = null;
        for(Map.Entry<Integer, List<Bid>> entry : bidMap.entrySet()){
            List<Bid> bidsForAmount = entry.getValue();
            if(bidsForAmount.size() ==1){
                winningBid = bidsForAmount.get(0);
                break;
            }
        }

        if(winningBid == null){
            System.out.println("No winner for event " + eventId + " (no unique lowest bid found)");
            return;
        }

        Winner winner = new Winner(eventId, winningBid.memberId, winningBid.amount,event.date);

        winners.add(winner);
        System.out.println("Winner of event " + eventId + " is: " + winningBid.memberId + " with bid: " + winningBid.amount);
    }

    // --- LIST WINNERS----
    public void listWinners(String orderBy){
        if(winners.isEmpty()){
            System.out.println("No winners yet.");
            return;
        }
        List<Winner> sorted = new ArrayList<>(winners);

        if(orderBy.equalsIgnoreCase("asc")){
            sorted.sort(Comparator.comparingInt(w->w.lowestBid));
        }else if(orderBy.equalsIgnoreCase("desc")){
            sorted.sort((a,b) -> b.lowestBid - a.lowestBid);
        }

        System.out.println("Winners List (" + orderBy + "):");

        for(Winner w : sorted){
            System.out.println(" " + w);
        }
    }

    // -- VIEW MEMBER WALLET ----
    public void viewWallet(String memberId){
        if(!members.containsKey(memberId)){
            System.out.println("ERROR: Member not found.");
            return;
        }
        System.out.println(memberId + " has " + members.get(memberId).superCoins + " super coins.");
    }

}

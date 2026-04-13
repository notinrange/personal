package org.example;

import org.example.service.BidBlitzService;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        BidBlitzService service = new BidBlitzService();

        System.out.println("=========TEST CASE 1==========");
        service.addMember("member1",1000);
        service.addMember("member2",800);
        service.addMember("member3",500);

        service.createEvent("event1", "2024-12-01");

        service.submitBids("member1","event1", Arrays.asList(100,500,400,800,900));
        service.submitBids("member2","event1", Arrays.asList(100,200,300));
        service.submitBids("member3","event1", Arrays.asList(50,75));

        service.declareWinner("event1");

        // Bid analysis:
        // 50  -> only member3  -> UNIQUE ✅ (lowest unique)
        // 75  -> only member3  -> unique but higher than 50
        // 100 -> member1 AND member2 -> NOT unique
        // 200 -> only member2 -> unique
        // 300 -> only member2 -> unique
        // 400 -> only member1 -> unique
        // 500 -> only member1 -> unique
        // 800 -> only member1 -> unique
        // 900 -> only member1 -> unique
        // Winner = member3 with bid 50

        System.out.println("\n======== TEST CASE 2 ==============");
        service.addMember("member4",200);
        service.addMember("member5",200);

        service.createEvent("event2", "2024-12-02");

        service.submitBids("member4","event2", Arrays.asList(150,180));
        service.submitBids("member5","event2", Arrays.asList(150,160));

        service.declareWinner("event2");

        // Bid analysis:
        // 150 -> member4 AND member5 -> NOT unique
        // 160 -> only member5 -> UNIQUE ✅ (lowest unique)
        // 180 -> only member4 -> unique

        System.out.println("\n============= TEST CASE 3: Edge Cases =================");
        service.addMember("member6",5000);
        service.createEvent("event3","2024-12-03");
        service.submitBids("member6","event3",Arrays.asList(10, 20, 30, 40, 50, 60));

        service.addMember("member7",50);
        service.submitBids("member7","event1",Arrays.asList(10,100));


        System.out.println("\n======= LIST WINNERS =========");
        service.listWinners("asc");
        service.listWinners("desc");

        System.out.println("\n======= WALLET CHECK ==========");
        service.viewWallet("member1");
        service.viewWallet("member3");
    }
}

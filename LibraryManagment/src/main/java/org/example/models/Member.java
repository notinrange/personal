package org.example.models;

import org.example.enums.MembershipType;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String memberId;
    private String name;
    private MembershipType membershipType;
    private List<BorrowRecord> activeBorrows;

    public Member(String memberId,String name,MembershipType type){
        this.memberId = memberId;
        this.name = name;
        this.membershipType = type;
        this.activeBorrows = new ArrayList<>();
    }

    public String getMemberId() { return memberId;}
    public String getName(){return  name;}
    public MembershipType getMembershipType(){return membershipType;}
    public List<BorrowRecord> getActiveBorrows(){return  activeBorrows;}

    public int getBorrowLimit(){
        return membershipType == MembershipType.PREMIUM ? 5 : 3;
    }

    @Override
    public String toString(){
        return "[" + memberId + "]" + name + " (" + membershipType + ")";
    }
}

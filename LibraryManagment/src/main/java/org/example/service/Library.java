package org.example.service;

import org.example.enums.BookStatus;
import org.example.models.BookItem;
import org.example.models.BorrowRecord;
import org.example.models.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    private  static Library instance;

    private Map<String, BookItem> bookItems;
    private Map<String, Member> members;
    private List<BorrowRecord> allRecords;

    private Library(){
        bookItems = new HashMap<>();
        members = new HashMap<>();
        allRecords = new ArrayList<>();
    }

    public static Library getInstance(){
        if(instance == null){
            instance = new Library();
        }
        return  instance;
    }

    public void addBookItem(BookItem item){
        bookItems.put(item.getItemId(), item);
        System.out.println("Added: " + item.getBook());
    }

    public void registerMember(Member member){
        members.put(member.getMemberId(), member);
        System.out.println("Register member: " + member);
    }


    public List<BookItem> searchByTitle(String title){
        List<BookItem> result = new ArrayList<>();
        for(BookItem item : bookItems.values()){
            if(item.getBook().getTitle().toLowerCase().contains(title.toLowerCase())){
                result.add(item);
            }
        }
        return  result;
    }

    public List<BookItem> searchByAuthor(String author){
        List<BookItem> result = new ArrayList<>();
        for(BookItem item : bookItems.values()){
            if(item.getBook().getAuthor().toLowerCase().contains(author.toLowerCase())){
                result.add(item);
            }
        }
        return  result;
    }

    public BorrowRecord borrowBook(String memberId, String itemId){
        Member member = members.get(memberId);
        BookItem item = bookItems.get(itemId);

        if(member == null) throw new RuntimeException(" Member not found: " + memberId);

        if(item==null) throw new RuntimeException(" Book item not found: " + itemId);

        if(member.getActiveBorrows().size() >= member.getBorrowLimit()){
            throw new RuntimeException(member.getName() + " has reached borrow limit of " + member.getBorrowLimit());
        }

        if(item.getStatus() != BookStatus.AVAILABLE){
            throw new RuntimeException(" Book is not available. Status: " + item.getStatus());
        }

        BorrowRecord record = new BorrowRecord(member, item);
        item.setStatus(BookStatus.BORROWED);
        member.getActiveBorrows().add(record);
        allRecords.add(record);

        System.out.println("Borrowed: " + item.getBook().getTitle() + " by " + member.getName() + " | Due: " + record.getDueDate());
        return  record;
    }

    public double returnBook(String memberId,String itemId){
        Member member = members.get(memberId);
        BookItem item = bookItems.get(itemId);

        if(member == null) throw  new RuntimeException("Member not found");
        if(item == null) throw new RuntimeException("Book item not found");

        BorrowRecord record = member.getActiveBorrows().stream().filter(r -> r.getBookItem().getItemId().equals(itemId)).findFirst().orElseThrow(() -> new RuntimeException("No active borrow record found"));

        record.markReturned();
        item.setStatus(BookStatus.AVAILABLE);
        member.getActiveBorrows().remove(record);

        double fine = record.calculateFine();
        System.out.println("Returned: " + item.getBook().getTitle() + " | Fine: ₹" + fine);
        return fine;
    }

    public void printAllRecords(){
        System.out.println("\n---- All Borrow Records -----");
        allRecords.forEach(System.out::println);
    }

    public void printAvailableBooks(){
        System.out.println("\n------ Available Books ---------");
        bookItems.values().stream().filter(item-> item.getStatus() == BookStatus.AVAILABLE).forEach(item-> System.out.println(item.getBook() + " | " + item.getItemId()));
    }
}

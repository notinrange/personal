package org.example.models;

import java.time.LocalDate;
import java.util.UUID;

public class BorrowRecord {
    private String recordId;
    private Member member;
    private BookItem bookItem;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;


    private static final int BORROW_DAYS = 14;
    private static final double FINE_PER_DAY = 5.0;

    public BorrowRecord(Member member,BookItem bookItem){
        this.recordId = UUID.randomUUID().toString().substring(0,8);
        this.member = member;
        this.bookItem = bookItem;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(BORROW_DAYS);
        this.returnDate = null;
    }

    public String getRecordId() { return recordId;}
    public Member getMember(){ return member;}
    public BookItem getBookItem(){ return  bookItem;}
    public LocalDate getDueDate(){return  dueDate;}
    public LocalDate getReturnDate(){return returnDate;}

    public void markReturned(){
        this.returnDate = LocalDate.now();
    }

    public double calculateFine(){
        LocalDate checkDate = returnDate != null ? returnDate : LocalDate.now();
        if(!checkDate.isAfter(dueDate))return 0;
        long overdueDays = dueDate.until(checkDate).getDays();
        return overdueDays * FINE_PER_DAY;
    }

    @Override
    public String toString(){

        return "Record[" + recordId + "] | " + member.getName()
                + " | " + bookItem.getBook().getTitle()
                + " | Due : " + dueDate
                + (returnDate != null ? " | Returned: " + returnDate : " | Not returned yet" );
    }
}

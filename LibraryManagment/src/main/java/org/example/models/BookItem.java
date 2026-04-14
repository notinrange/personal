package org.example.models;

import org.example.enums.BookStatus;

public class BookItem {
    private String itemId;
    private Book book;
    private BookStatus status;

    public BookItem(String itemId, Book book){
        this.itemId = itemId;
        this.book = book;
        this.status = BookStatus.AVAILABLE;
    }

    public String getItemId() { return itemId;}
    public Book getBook() { return book;}
    public BookStatus getStatus(){return status;}
    public void setStatus(BookStatus status){
        this.status = status;
    }
}

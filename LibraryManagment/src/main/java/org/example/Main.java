package org.example;

import org.example.enums.MembershipType;
import org.example.models.Book;
import org.example.models.BookItem;
import org.example.models.BorrowRecord;
import org.example.models.Member;
import org.example.service.Library;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Library library = Library.getInstance();

        Book b1 = new Book("ISBN001", "Clean Code","Robert Martin");
        Book b2 = new Book("ISBN002","System Design Interview", "Alex Xu");
        Book b3 = new Book("ISBN003","Effective Java", "joshua Bloch");

        library.addBookItem(new BookItem("ITEM001",b1));
        library.addBookItem(new BookItem("ITEM002",b2));
        library.addBookItem(new BookItem("ITEM003",b2));
        library.addBookItem(new BookItem("ITEM004",b3));


        Member m1 = new Member("M001", "Rahul Kumar", MembershipType.PREMIUM);
        Member m2 = new Member("M002", "Priya Sharma", MembershipType.BASIC);
        library.registerMember(m1);
        library.registerMember(m2);

        System.out.println();
        System.out.println("---- Search: 'clean ----");
        library.searchByTitle("clean").forEach(bookItem -> System.out.println(bookItem.getBook() + " | Status: " + bookItem.getStatus()));

        System.out.println();

        BorrowRecord r1 = library.borrowBook("MOO1", "ITEM001");
        BorrowRecord r2 = library.borrowBook("M002", "ITEM003");


        System.out.println();

        try{
            library.borrowBook("M002", "ITEM003");
        }catch (RuntimeException e){
            System.out.println("Expected error: " + e.getMessage());
        }

        System.out.println();

        library.returnBook("M001","ITEM001");
        library.returnBook("M002","ITEM003");

        System.out.println();

        library.printAvailableBooks();

        library.printAvailableBooks();
    }
}

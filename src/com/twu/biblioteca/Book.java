package com.twu.biblioteca;

/**
 * Created by xuzhang on 3/2/15.
 */
public class Book {
    private String name;

    private String author;

    private int publishYear;

    private User borrowUser = null;

    public boolean isBorrowOut() {
        return borrowUser != null;
    }

    public void borrowOut(User user) {
        borrowUser = user;
    }

    public void returnBack() {
        borrowUser = null;
    }

    public String getName() {
        return name;
    }

    public Book(String name, String author, int publishYear) {
        this.name = name;
        this.author = author;
        this.publishYear = publishYear;
        this.borrowUser = null;
    }

    @Override
    public String toString() {
        return name + " | " + author + " | " + publishYear;
    }
}

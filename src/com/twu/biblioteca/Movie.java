package com.twu.biblioteca;

/**
 * Created by xuzhang on 3/4/15.
 */
public class Movie {
    private String name;
    private int year;
    private String director;
    private int rating = -1;
    private User borrowUser = null;

    public boolean isBorrowOut() {
        return borrowUser != null;
    }

    public void borrowOut(User user) {
        this.borrowUser = user;
    }

    public Movie(String name, int year, String director, int rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public Movie(String name, int year, String director) {
        this.name = name;
        this.year = year;
        this.director = director;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        String result = name + " | " + year + " | " + director;
        if(rating > 0) {
            result += " | " + rating;
        }
        return result;
    }
}

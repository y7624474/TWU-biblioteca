package com.twu.biblioteca;

/**
 * Created by xuzhang on 3/5/15.
 */
public class User {
    private String name;
    private String libraryNum;
    private String emailAddress;
    private String phoneNum;
    private String password;

    public User(String name, String libraryNum, String emailAddress, String phoneNum, String password) {
        this.name = name;
        this.libraryNum = libraryNum;
        this.emailAddress = emailAddress;
        this.phoneNum = phoneNum;
        this.password = password;
    }

    public boolean checkUser(String libraryNum, String userPassword) {
        return this.libraryNum.equals(libraryNum) && this.password.equals(userPassword);
    }

    @Override
    public String toString() {
        return "name: " + name + "\nemail: " + emailAddress + "\nphone: " + phoneNum;
    }
}

package com.twu.biblioteca.com.twu.biblioteca.util;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by xuzhang on 3/7/15.
 */
public class StringUtils {
    public static final String WELCOME_MESSAGE = "welcome to use biblioteca";

    public static final String MAIN_MENU_OPTION_CHOOSE_PROMPT = "Main Menu(select one " +
            "options below, such as 1 or 2):";
    public static final String MAIN_MENU_INVALID_INPUT_PROMPT = "Select an invalid option, retry please:";

    public static final String LOGIN_PAGE_INPUT_USER_PROMPT = "please input username(library num)";
    public static final String LOGIN_PAGE_INPUT_PASSWORD_PROMPT = "please input password";
    public static final String LOGIN_PAGE_LOGIN_SUCCESS_MSG = "login success";
    public static final String LOGIN_PAGE_LOGIN_FAIL_MSG = "login fail, user not exist";

    public static final String SHOW_BOOK_LIST = "Book List:";
    public static final String SHOW_MOVIE_LIST = "Movie List:";

    public static final String CHECKOUT_MOVIE_BY_NAME_PROMPT = "please checkout movie, input movie name";
    public static final String CHECKOUT_MOVIE_SUCCESS_MSG = "enjoy movie at home";
    public static final String CHECKOUT_MOVIE_FAIL_MSG = "That movie is not available.";

    public static final String CHECKOUT_BOOK_BY_NAME_PROMPT = "please checkout book(type book name)";
    public static final String CHECKOUT_BOOK_SUCCESS_MSG = "Thank you! Enjoy the book";
    public static final String CHECKOUT_BOOK_FAIL_MSG = "That book is not available.";
    public static final String RETURN_BOOK_BY_NAME_PROMPT = "please return book(type book name)";
    public static final String RETURN_BOOK_SUCCESS_MSG = "Thank you for returning the book.";
    public static final String RETURN_BOOK_FAIL_MSG = "That is not a valid book to return.";

    public static final String USER_QUIT_COMMAND = "quit";
}

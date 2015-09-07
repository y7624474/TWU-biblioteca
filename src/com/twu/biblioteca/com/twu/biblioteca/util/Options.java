package com.twu.biblioteca.com.twu.biblioteca.util;

import com.twu.biblioteca.BibliotecaApp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuzhang on 3/8/15.
 */
public enum Options {
    SHOW_BOOK_LIST(1, ": Display Book List") {
        @Override
        public void execute(BibliotecaApp app) {
            app.showBookList();
        }
    },
    CHECKOUT_BOOK_OPTION(2, ": Checkout Book") {
        @Override
        public void execute(BibliotecaApp app) {
            app.enterCheckoutBookMenu();
        }
    },
    RETURN_BOOK_OPTION(3, ": Return Book") {
        @Override
        public void execute(BibliotecaApp app) {
            app.enterReturnBookMenu();
        }
    },
    SHOW_MOVIE_LIST_OPTION(4, ": Show Movie List") {
        @Override
        public void execute(BibliotecaApp app) {
            app.showMovieList();
        }
    },
    CHECKOUT_MOVIE_OPTION(5, ": Checkout Movie(type movie name)") {
        @Override
        public void execute(BibliotecaApp app) {
            app.enterCheckoutMovieMenu();
        }
    },
    SHOW_USER_INFO_OPTION(6, ": Show My Profile") {
        @Override
        public void execute(BibliotecaApp app) {
            app.showLoginUserInformation();
        }
    },
    UNKNOW_OPTION(-1, "") {
        @Override
        public void execute(BibliotecaApp app) {
            app.getMainMenu().invalidOptionPromptMessage();
        }
    };

    private final String msg;
    private final int index;
    private static final Map<String, Options> stringToOptions =
            new HashMap<String, Options>();

    static {
        for(Options op: Options.values()) {
            stringToOptions.put(op.toString(), op);
        }
    }

    public static Options fromString(String symbol) {
        return stringToOptions.get(symbol) != null ? stringToOptions.get(symbol) : UNKNOW_OPTION;
    }

    private Options(int index, String msg) {
        this.index = index;
        this.msg = msg;
    }

    public String toString() {
        return index + "";
    }

    public String showOptionMsg() {
        return index + msg;
    }

    public abstract void execute(BibliotecaApp app);
}

package com.twu.biblioteca;

import com.twu.biblioteca.com.twu.biblioteca.util.Options;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class BibliotecaAppTest {
    private BibliotecaApp bibliotecaApp;

    @Before
    public void generateInitBooksInLib() {
        bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.setLoginUser(new User("yxl", "xian-001", "yy@163.com","89893843", "123456"));
    }

    @Test
    public void testSeeWelcomeMessageAndMainMenuWhenAppStartup() {
        StringBuilder startMessage = new StringBuilder();
        showWelcomeMessage(startMessage);
        showMainMenu(startMessage);

        ByteArrayOutputStream output = setSystemOutput();

        bibliotecaApp.displayHomePage();
        assertEquals(startMessage.toString(), output.toString());
    }

    @Test
    public void testWhenUserSelectOnePrintBookList() {
        StringBuilder expect = new StringBuilder();
        showBookList(expect);
        ByteArrayOutputStream output = setSystemOutput();

        Options.fromString("1").execute(bibliotecaApp);
        assertEquals(expect.toString(), output.toString());
    }

    @Test
    public void testSelectShowMovieOption() {
        StringBuilder expect = new StringBuilder();
        showMovieList(expect);
        ByteArrayOutputStream output = setSystemOutput();

        Options.fromString(Options.SHOW_MOVIE_LIST_OPTION.toString()).execute(bibliotecaApp);
        assertEquals(expect.toString(), output.toString());
    }

    @Test
    public void testWhenUserSelectInvalidOptionShowWarningMessage() {
        bibliotecaApp.initSomeBooksToLib();
        StringBuilder expect = new StringBuilder();
        showInvalidOptionWarningMessage(expect);

        ByteArrayOutputStream output = setSystemOutput();

        Options.fromString("22").execute(bibliotecaApp);
        assertEquals(expect.toString(), output.toString());
    }

    @Test
    public void testContinueChooseOptionUntilPressQuit() {
        StringBuilder expect = new StringBuilder();
        showWelcomeMessage(expect);
        showMainMenu(expect);
        showBookList(expect);
        showInvalidOptionWarningMessage(expect);
        showBookList(expect);
        ByteArrayInputStream input = setSystemInput(Options.SHOW_BOOK_LIST.toString() +
                        "\n" + Options.UNKNOW_OPTION.toString() + "\n"
                        + Options.SHOW_BOOK_LIST + "\nquit");

        ByteArrayOutputStream output = setSystemOutput();
        bibliotecaApp.run();

        assertEquals(expect.toString(), output.toString());
    }

    @Test
    public void testCheckoutBookAndTheBookNotAppearsInLibBookList() {
        bibliotecaApp.addANewBookToLibrary("Ruby on Rails", "Dave Thomas", 2007);
        Book checkBook = getBookInLastOfBookList();
        bibliotecaApp.checkoutBook(checkBook.getName());
        bibliotecaApp.showBookList();

        ByteArrayOutputStream bookList = setSystemOutput();
        assertTrue(bookList.toString().indexOf(checkBook.toString()) == -1);
    }

    @Test
    public void testCheckoutBookSuccessShowSuccessMessage() {
        StringBuilder expect = new StringBuilder();
        showCheckoutBookPromptMessage(expect);
        expect.append("Thank you! Enjoy the book\n");

        ByteArrayInputStream inputBookName = setSystemInput("C++ Primer");
        ByteArrayOutputStream output = setSystemOutput();
        Options.fromString(Options.CHECKOUT_BOOK_OPTION.toString()).execute(bibliotecaApp);
        assertEquals(expect.toString(), output.toString());
    }

    @Test
    public void testCheckoutBookFailShowPromptMessage() {
        StringBuilder expect = new StringBuilder();
        showCheckoutBookPromptMessage(expect);
        expect.append("That book is not available.\n");

        ByteArrayInputStream inputBookName = setSystemInput("C Primer");
        ByteArrayOutputStream output = setSystemOutput();
        Options.fromString(Options.CHECKOUT_BOOK_OPTION.toString()).execute(bibliotecaApp);
        assertEquals(expect.toString(), output.toString());
    }

    @Test
    public void testReturnBook() {
        Book book = getBookInLastOfBookList();
        bibliotecaApp.checkoutBook(book.getName());
        assertTrue(book.isBorrowOut());
        bibliotecaApp.returnBook(book.getName());
        assertFalse(book.isBorrowOut());
    }

    @Test
    public void testSeeReturnBookOptionInMainMenu() {
        StringBuilder checkoutMessage = new StringBuilder();
        checkoutMessage.append("3: Return Book\n");

        ByteArrayOutputStream output = setSystemOutput();
        bibliotecaApp.getMainMenu().show();

        assertTrue(isOutputContainExpect(output, checkoutMessage));
    }

    @Test
    public void testCheckoutMovie() {
        bibliotecaApp.addANewMovieToLibrary("Source code", 2003, "Dave Selm", 9);
        StringBuilder expect = new StringBuilder();
        expect.append("enjoy movie at home\n");

        ByteArrayOutputStream output = setSystemOutput();
        bibliotecaApp.checkoutMovie("Source code");
        assertEquals(expect.toString(), output.toString());
    }

    @Test
    public void testReturnBookSuccessShowPromptMessage() {
        Book book = getBookInLastOfBookList();
        bibliotecaApp.checkoutBook(book.getName());
        ByteArrayOutputStream output = setSystemOutput();
        bibliotecaApp.returnBook(book.getName());
        assertEquals(output.toString(), "Thank you for returning the book.\n");
    }

    @Test
    public void testReturnBookFailShowPromptMessage() {
        Book bookNotInBookList = new Book("HeadFirst Python", "Dahl", 2008);
        ByteArrayOutputStream output = setSystemOutput();
        bibliotecaApp.returnBook(bookNotInBookList.getName());
        assertEquals(output.toString(), "That is not a valid book to return.\n");
    }

    @Test
    public void testUserLoginLibrarySuccessShowMainMenu() {
        StringBuilder expect = new StringBuilder();
        expect.append("please input username(library num)\n");
        expect.append("please input password\n");
        expect.append("login success\n");
        showMainMenu(expect);
        ByteArrayInputStream input = setSystemInput("xian-001\n123456");
        ByteArrayOutputStream outputStream = setSystemOutput();
        bibliotecaApp.loginPage();
        assertEquals(expect.toString(), outputStream.toString());
    }

    @Test
    public void testUserLoginFailFirstTime() {
        StringBuilder expect = new StringBuilder();
        expect.append("please input username(library num)\n");
        expect.append("please input password\n");
        expect.append("login fail, user not exist\n");
        ByteArrayInputStream input = setSystemInput("xili\n333\nxian-001\n123456");
        ByteArrayOutputStream outputStream = setSystemOutput();
        bibliotecaApp.loginPage();
        assertTrue(isOutputContainExpect(outputStream, expect));
    }

    @Test
    public void testChooseShowUserInformationOption() {
        StringBuilder expect = new StringBuilder();
        expect.append(bibliotecaApp.getLoginUser().toString() + "\n");

        ByteArrayOutputStream outputStream = setSystemOutput();
        Options.fromString(Options.SHOW_USER_INFO_OPTION.toString()).execute(bibliotecaApp);
        assertEquals(expect.toString(), outputStream.toString());
    }

    private Book getBookInLastOfBookList() {
        List<Book> books = bibliotecaApp.getBooksList();
        return books.get(books.size()-1);
    }

    private void showMovieList(StringBuilder expect) {
        expect.append("Movie List:\n");
        expect.append("Tomorrow | 2010 | Speberg | 9\n");
        expect.append("Spring | 2003 | Steve\n");
        expect.append("Money Ball | 2007 | Royn Smith | 8\n");
    }

    private void showCheckoutBookPromptMessage(StringBuilder stringBuilder) {
        stringBuilder.append("please checkout book(type book name)\n");
    }

    private ByteArrayOutputStream setSystemOutput() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        return output;
    }

    private ByteArrayInputStream setSystemInput(String inputVal) {
        ByteArrayInputStream in = new ByteArrayInputStream(inputVal.getBytes());
        System.setIn(in);
        return in;
    }

    private void showMainMenu(StringBuilder stringBuilder) {
        stringBuilder.append("Main Menu(select one options below, such as 1 or 2):\n");
        stringBuilder.append("1: Display Book List\n");
        stringBuilder.append("2: Checkout Book\n");
        stringBuilder.append("3: Return Book\n");
        stringBuilder.append("4: Show Movie List\n");
        stringBuilder.append("5: Checkout Movie(type movie name)\n");
        stringBuilder.append("6: Show My Profile\n");
    }

    private void showWelcomeMessage(StringBuilder stringBuilder) {
        stringBuilder.append("welcome to use biblioteca\n");
    }

    private void showBookList(StringBuilder stringBuilder) {
        stringBuilder.append("Book List:\n");
        stringBuilder.append("C++ Primer | Bob | 1998\n");
        stringBuilder.append("Java HeadFirst | Luce | 2007\n");
    }

    private void showInvalidOptionWarningMessage(StringBuilder stringBuilder) {
        stringBuilder.append("Select an invalid option, retry please:\n");
    }

    private boolean isOutputContainExpect(ByteArrayOutputStream output, StringBuilder expect) {
        return output.toString().indexOf(expect.toString()) != -1;
    }
}
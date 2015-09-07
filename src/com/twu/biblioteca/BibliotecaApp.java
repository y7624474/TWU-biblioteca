package com.twu.biblioteca;

import com.twu.biblioteca.com.twu.biblioteca.util.Options;
import com.twu.biblioteca.com.twu.biblioteca.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private List<Book> books = new ArrayList<Book>();
    private List<Movie> movies = new ArrayList<Movie>();
    private User loginUser = null;
    private List<User> users = new ArrayList<User>();
    private MainMenu mainMenu = new MainMenu();

    public BibliotecaApp() {
        initSomeBooksToLib();
        initSomeMoviesToLib();
        initUsers();
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    private void initUsers() {
        this.users.add(new User("yxl", "xian-001", "yy@163.com","89893843", "123456"));
        this.users.add(new User("lijian", "xian-002", "lijian@126.com", "81783742", "01234"));
        this.users.add(new User("yehua", "hei-003", "yehua@gmail.com", "62537214", "88888888"));
    }

    public List<Book> getBooksList() {
        return books;
    }

    public List<Movie> getMoviesList() {
        return movies;
    }

    public static void main(String[] args) {
        new BibliotecaApp().run();
    }

    public void run() {
        if(!checkUserLogin()) {
            loginPage();
        }
        displayHomePage();
        letUserChooseOption();
    }

    public boolean checkUserLogin() {
        return loginUser != null;
    }

    public User getLoginUser() {
        return loginUser;
    }

    private void letUserChooseOption() {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        while(!option.equals(StringUtils.USER_QUIT_COMMAND)) {
            Options.fromString(option).execute(this);
            option = scanner.nextLine();
        }
    }

    public void loginPage() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(StringUtils.LOGIN_PAGE_INPUT_USER_PROMPT);
            String userLibraryNum = scanner.nextLine();
            System.out.println(StringUtils.LOGIN_PAGE_INPUT_PASSWORD_PROMPT);
            String password = scanner.nextLine();
            User loginUser = findUser(userLibraryNum, password);
            if(loginUser != null) {
                System.out.println(StringUtils.LOGIN_PAGE_LOGIN_SUCCESS_MSG);
                setLoginUser(loginUser);
                mainMenu.show();
                break;
            } else {
                System.out.println(StringUtils.LOGIN_PAGE_LOGIN_FAIL_MSG);
            }
        }
    }

    private User findUser(String libraryNum, String password) {
        for(User user : users) {
            if(user.checkUser(libraryNum, password)) {
                return user;
            }
        }
        return null;
    }

    public void showLoginUserInformation() {
        System.out.println(loginUser);
    }

    public void enterCheckoutMovieMenu() {
        showCheckMoviePromptMessage();
        Scanner checkoutScanner = new Scanner(System.in);
        String movieName = checkoutScanner.nextLine();
        checkoutMovie(movieName);
    }

    public void checkoutMovie(String movieName) {
        Movie checkoutMovie = findMovieInMovieListByName(movieName);
        if(findMovieInMovieListByName(movieName) != null) {
            checkoutMovie.borrowOut(getLoginUser());
            showCheckMovieSuccessMessage();
        } else {
            showCheckMovieFailMessage();
        }
    }

    public void showBookList() {
        System.out.println(StringUtils.SHOW_BOOK_LIST);
        for(Book book : books) {
            if(!book.isBorrowOut()){
                System.out.println(book);
            }
        }
    }

    public void showMovieList() {
        System.out.println(StringUtils.SHOW_MOVIE_LIST);
        for(Movie movie : movies) {
            if(!movie.isBorrowOut()) {
                System.out.println(movie);
            }
        }
    }

    public void enterCheckoutBookMenu() {
        showCheckBookPromptMessage();
        Scanner checkoutScanner = new Scanner(System.in);
        String bookName = checkoutScanner.nextLine();
        checkoutBook(bookName);
    }

    public void checkoutBook(String bookName) {
        Book checkout = findBookInBookListByName(bookName);
        if(checkout != null){
            checkout.borrowOut(getLoginUser());
            showBorrowBookSuccessMessage();
        } else {
            showBorrowBookFailMessage();
        }
    }

    public void enterReturnBookMenu() {
        showReturnBookPromptMessage();
        Scanner returnScanner = new Scanner(System.in);
        String bookName = returnScanner.nextLine();
        returnBook(bookName);
    }

    public void returnBook(String bookName) {
        Book checkout = findBookInBookListByName(bookName);
        if(checkout != null){
            checkout.returnBack();
            showReturnBookSuccessMessage();
        } else {
            showReturnBookFailMessage();
        }
    }

    public void showWelcomeMessage() {
        System.out.println(StringUtils.WELCOME_MESSAGE);
    }

    public void displayHomePage() {
        showWelcomeMessage();
        mainMenu.show();
    }

    public void initSomeBooksToLib() {
        addANewBookToLibrary("C++ Primer", "Bob", 1998);
        addANewBookToLibrary("Java HeadFirst", "Luce", 2007);
    }

    public void addANewBookToLibrary(String name, String author, int publishYear) {
        this.books.add(new Book(name, author, publishYear));
    }

    public void initSomeMoviesToLib() {
        addANewMovieToLibrary("Tomorrow", 2010, "Speberg", 9);
        addANewMovieToLibrary("Spring", 2003, "Steve");
        addANewMovieToLibrary("Money Ball", 2007, "Royn Smith", 8);
    }

    public void addANewMovieToLibrary(String name, int year, String director, int rating) {
        this.movies.add(new Movie(name, year, director, rating));
    }

    public void addANewMovieToLibrary(String name, int year, String director) {
        this.movies.add(new Movie(name, year, director));
    }

    public void setLoginUser(User user) {
        this.loginUser = user;
    }

    private Book findBookInBookListByName(String bookName) {
        for(Book book: books) {
            if(book.getName().equalsIgnoreCase(bookName)) {
                return book;
            }
        }
        return null;
    }

    private Movie findMovieInMovieListByName(String movieName) {
        for(Movie movie: movies) {
            if(movie.getName().equalsIgnoreCase(movieName)) {
                return movie;
            }
        }
        return null;
    }

    private void showReturnBookPromptMessage() {
        System.out.println(StringUtils.RETURN_BOOK_BY_NAME_PROMPT);
    }

    private void showReturnBookSuccessMessage() {
        System.out.println(StringUtils.RETURN_BOOK_SUCCESS_MSG);
    }

    private void showReturnBookFailMessage() {
        System.out.println(StringUtils.RETURN_BOOK_FAIL_MSG);
    }

    private void showCheckBookPromptMessage() {
        System.out.println(StringUtils.CHECKOUT_BOOK_BY_NAME_PROMPT);
    }

    private void showBorrowBookSuccessMessage() {
        System.out.println(StringUtils.CHECKOUT_BOOK_SUCCESS_MSG);
    }

    private void showBorrowBookFailMessage() {
        System.out.println(StringUtils.CHECKOUT_BOOK_FAIL_MSG);
    }

    private void showCheckMoviePromptMessage() {
        System.out.println(StringUtils.CHECKOUT_MOVIE_BY_NAME_PROMPT);
    }

    private void showCheckMovieFailMessage() {
        System.out.println(StringUtils.CHECKOUT_MOVIE_FAIL_MSG);
    }

    private void showCheckMovieSuccessMessage() {
        System.out.println(StringUtils.CHECKOUT_MOVIE_SUCCESS_MSG);
    }
}

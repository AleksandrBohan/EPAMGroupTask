package com.epam.jwd.view;

import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.repository.model.User;

import java.util.List;

public class Menu {
    private static final String DELIMITER = "---------------------";

    private static final String MENU_FOR_GUEST = """ 
            Select:
            1.Sign In
            2.Registration
            """;
    private static final String MENU_FOR_USER = """
            Select:
            1.Buy a ticket
            2.Your tickets
            2.Check balance
            3.Profile settings
            4.Sign Out
            """;

    private static final String MENU_PROFILE_SETTINGS = """ 
            Select:
            1.Change name
            2.Change age
            3.Change email
            4.Back
            """;
    private static final String MOVIE_NAME = "Movie name: ";
    private static final String GENRE = "Genre: ";
    private static final String PRICE = "Price: ";
    private static final String AVAILABLE_FOR_KIDS = "Allowed for children";
    private static final String NOT_AVAILABLE_FOR_KIDS = "Not allowed for children";
    private static final String TICKETS_AVAILABLE = "Tickets available";
    private static final String TICKETS_NOT_AVAILABLE = "Tickets not available";
    private static final String PERSON_NAME_ENTER = "Enter your name: ";
    private static final String PERSON_NEW_NAME_ENTER = "Enter new name: ";
    private static final String PERSON_NEW_EMAIL_ENTER = "Enter new email: ";
    private static final String PERSON_BALANCE_ENTER = "Enter your balance: ";
    private static final String PERSON_AGE_ENTER = "Enter your age: ";
    private static final String PERSON_NEW_AGE_ENTER = "Enter new age: ";
    private static final String PERSON_EMAIL_ENTER = "Enter your email: ";
    private static final String SUCCESSFUL_REGISTRATION = ", you have successfully registered.";
    private static final String SUCCESSFUL_NAME_CHANGE = "You have successfully changed your name";
    private static final String SUCCESSFUL_AGE_CHANGE = "You have successfully changed your name";
    private static final String SUCCESSFUL_EMAIL_CHANGE = "You have successfully changed your name";
    private static final String HELLO = "Hello, ";
    private static final String USER_NAME = "User name: ";
    private static final String AGE = "Age: ";
    private static final String EMAIL = "Email: ";
    private static final String BALANCE = "Balance: ";
    private static final String BYE_MESSAGE = "Bye, ";
    private static final String SELECT_MOVIE_MESSAGE = "Select movie: ";
    private static final String NO_TICKETS_MESSAGE = "No tickets purchased.";

    public static void getProfileSettingsMenu() {
        System.out.println(MENU_PROFILE_SETTINGS);
    }

    public static void getGuestMenu() {
        System.out.println(MENU_FOR_GUEST);
    }

    public static void getUserMenu(String userName) {
        getMessageWelcome(userName);
        System.out.println(MENU_FOR_USER);
    }

    public static void getMenuMovies(Ticket ticket) {
        System.out.println(DELIMITER);
        getMessageMovieName(ticket.getMovieName());
        getMessageMovieGenre(ticket.getMovieGenre());
        getMessageMoviePrice(ticket.getPrice());

        if (ticket.isAvailable()) {
            getMessageAvailableTickets();
        } else {
            getMessageNotAvailableTickets();
        }

        if (ticket.isAvailableForKids()) {
            getMessageAvailableForKids();
        } else {
            getMessageNotAvailableForKids();
        }
        System.out.println(DELIMITER);
    }

    public static void getMessageMovieName(String movieName) {
        System.out.println(MOVIE_NAME);
    }

    public static void getMessageMovieGenre(String genre) {
        System.out.println(GENRE);
    }

    public static void getMessageMoviePrice(double price) {
        System.out.println(PRICE);
    }

    public static void getMessageAvailableForKids() {
        System.out.println(AVAILABLE_FOR_KIDS);
    }

    public static void getMessageNotAvailableForKids() {
        System.out.println(NOT_AVAILABLE_FOR_KIDS);
    }

    public static void getMessageAvailableTickets() {
        System.out.println(TICKETS_AVAILABLE);
    }

    public static void getMessageNotAvailableTickets() {
        System.out.println(TICKETS_NOT_AVAILABLE);
    }

    public static void getMessageInputUserName() {
        System.out.println(PERSON_NAME_ENTER);
    }

    public static void getMessageInputBalance() {
        System.out.println(PERSON_BALANCE_ENTER);
    }

    public static void getMessageInputAge() {
        System.out.println(PERSON_AGE_ENTER);
    }

    public static void getMessageInputEmail() {
        System.out.println(PERSON_EMAIL_ENTER);
    }

    public static void getMessageRegisteredSuccess(String userName) {
        System.out.println(userName + SUCCESSFUL_REGISTRATION);
    }

    public static void getMessageWelcome(String userName) {
        System.out.println(HELLO + userName);
    }

    public static void getMessageChangeUserName() {
        System.out.println(PERSON_NEW_NAME_ENTER);
    }

    public static void getMessageChangeUserNameSuccess() {
        System.out.println(SUCCESSFUL_NAME_CHANGE);
    }

    public static void getMessageChangeAge() {
        System.out.println(PERSON_NEW_AGE_ENTER);
    }

    public static void getMessageChangeAgeSuccess() {
        System.out.println(SUCCESSFUL_AGE_CHANGE);
    }

    public static void getMessageChangeEmail() {
        System.out.println(PERSON_NEW_EMAIL_ENTER);
    }

    public static void getMessageChangeEmailSuccess() {
        System.out.println(SUCCESSFUL_EMAIL_CHANGE);
    }

    public static void getMessageUserName(String userName) {
        System.out.println(USER_NAME + userName);
    }

    public static void getMessageAge(int age) {
        System.out.println(AGE + age);
    }

    public static void getMessageEmail(String email) {
        System.out.println(EMAIL + email);
    }

    public static void getMessageBalance(double balance) {
        System.out.println(BALANCE + balance);
    }

    public static void getInfoUser(User user) {
        getMessageUserName(user.getName());
        getMessageBalance(user.getBalance());
        getMessageAge(user.getAge());
        getMessageEmail(user.getEmail());
    }

    public static void getMessageGoodbye(String userName) {
        System.out.println(BYE_MESSAGE + userName);
    }

    public static void getMessageSelectTicket() {
        System.out.println(SELECT_MOVIE_MESSAGE);
    }

    public static void getMessageNotTickets() {
        System.out.println(NO_TICKETS_MESSAGE);
    }

    public static void getUserTickets(List<Ticket> tickets) {
        if(tickets.isEmpty()) {
            getMessageNotTickets();
        } else {
            tickets.forEach(Menu::getMenuMovies);
        }
    }
}

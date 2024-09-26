package com.epam.jwd.service.api;

import com.epam.jwd.repository.exception.NoFindMovieException;
import com.epam.jwd.repository.exception.UnavailableSaveUserException;
import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.exception.*;

import java.util.List;

public interface UserService {


    void registration(User user) throws UserNotFoundException, UnavailableSaveUserException;

    void buyTicket(String movieName) throws UnavailableTicketException, NoCashException, UserNotActiveException, NoFindMovieException;

    double checkTicketPrice(String movieName) throws NoFindMovieException;

    double getBalance() throws UserNotActiveException;

    void changeUserName(String userName) throws IllegalNameSizeException, UserNotActiveException;

    void changeUserAge(int age) throws IllegalAgeException, UserNotActiveException;

    void changeUserEmail(String userEmail) throws IllegalEmailException, UserNotActiveException;

    List<Ticket> getUsersTickets();

    List<Ticket> getAvailableTickets();

    List<Ticket> getAvailableForKidsTickets();

    List<Ticket> getTicketsByMovieName(String movieName);

    void signIn(String userName) throws UserNotFoundException;

    void signOut();
}

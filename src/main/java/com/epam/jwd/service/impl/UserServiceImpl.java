package com.epam.jwd.service.impl;

import com.epam.jwd.repository.api.TicketRepository;
import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.exception.NoFindMovieException;
import com.epam.jwd.repository.exception.UnavailableSaveUserException;
import com.epam.jwd.repository.impl.TicketRepositoryImpl;
import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.api.UserService;
import com.epam.jwd.service.exception.*;
import com.epam.jwd.service.validation.TicketValidation;
import com.epam.jwd.service.validation.UserValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import java.util.stream.Collectors;

import static com.epam.jwd.service.validation.UserValidation.isEmail;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private static final String USER_REGISTRATION = "User is registrated!";
    private static final String USER_BALANCE = "User balance will be checked!";
    private static final String OTHER_USER_NAME = "New username is valid and will be changed!";
    private static final String NORMAL_AGE = "Age isn't negative!";
    private static final String OTHER_USER_EMAIL = "New useremail is valid and will be changed!";
    private static final String TICKET_BUYING = "Ticket was bought!";
    private static final String TICKET_PRICE = "Ticket will be checked by the price!";
    private static final String MOVIE_NAME_ON_TICKET = "Tickets will be sorted by movie name!";
    private static final String USER_ACTIONS_IN = "User signed in!";
    private static final String USER_ACTIONS_OUT = "User signed out!";
    private static final String NOT_FOUND_MESSAGE_TEMPLATE = "User not found: ";
    private static final String NOT_FOUND_IN_REPOSITORY_MESSAGE = NOT_FOUND_MESSAGE_TEMPLATE + "User not found in repository.";
    private static final String NOT_FOUND_USERNAME_MESSAGE = NOT_FOUND_MESSAGE_TEMPLATE + "Not found user name : ";
    private static final String NOT_ACTIVE_MESSAGE = "User not active: ";
    private static final String NO_CASH_EXCEPTION_MESSAGE = "There is no money in your pocket to buy this ticket";
    private static final String ILLEGAL_NAME_SIZE_EXCEPTION_MESSAGE = "Name must be 1 or more symbols long";
    private static final String ILLEGAL_AGE_EXCEPTION_MESSAGE = "Age should be above 0";
    private static final String ILLEGAL_EMAIL_EXCEPTION_MESSAGE = "Enter valid email address";
    private static final String UNAVAILABLE_TICKET_EXCEPTION = "This ticket is not available";
    private static final String NO_FIND_MOVIE_EXCEPTION = "This film is not found";

    private User user;
    private final UserRepository<Long, User> userRepository = UserRepositoryImpl.getInstance();
    private final TicketRepository<Long, Ticket> ticketRepository = TicketRepositoryImpl.getInstance();

    @Override
    public void registration(User user) {
        logger.info(USER_REGISTRATION);

        try {
            userRepository.save(user);
        } catch (UnavailableSaveUserException e) {
            logger.error(e);
        }
        try {
            this.user = userRepository.findUser(user)
                    .orElseThrow(() ->
                    new UserNotFoundException(NOT_FOUND_IN_REPOSITORY_MESSAGE));
        } catch (UserNotFoundException e) {
            logger.error(e);
        }
        this.user.setActive(true);
    }


    public double getBalance() {
        if (!user.isActive()) {
            try {
                throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
            } catch (UserNotActiveException e) {
                logger.error(e);
            }
        }

        logger.info(USER_BALANCE);

        return user.getBalance();
    }

    @Override
    public void changeUserName(String userName) {
        if (!user.isActive()) {
            try {
                throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
            } catch (UserNotActiveException e) {
                logger.error(e);
            }
        }
        try {
            if (!UserValidation.isValidName(userName)) {
                throw new IllegalNameSizeException(ILLEGAL_NAME_SIZE_EXCEPTION_MESSAGE);
            }
        } catch (IllegalNameSizeException e) {
            logger.error(e);
        }

        logger.info(OTHER_USER_NAME);
        logger.debug(userName);

        user.setName(userName);
    }

    @Override
    public void changeUserAge(int age) {
        if (!user.isActive()) {
            try {
                throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
            } catch (UserNotActiveException e) {
                logger.error(e);
            }
        }

        try {
            if (!UserValidation.isValidAge(age)) {
                throw new IllegalAgeException(ILLEGAL_AGE_EXCEPTION_MESSAGE);
            }
        } catch (IllegalAgeException e) {
            logger.error(e);
        }

        logger.info(NORMAL_AGE);
        logger.debug(age);

        user.setAge(age);
    }

    @Override
    public void changeUserEmail(String userEmail) {
        if (!user.isActive()) {
            try {
                throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
            } catch (UserNotActiveException e) {
                logger.error(e);
            }
        }
        try {
            if (!isEmail(userEmail)) {
                throw new IllegalEmailException(ILLEGAL_EMAIL_EXCEPTION_MESSAGE);
            }
        } catch (IllegalEmailException e) {
            logger.error(e);
        }

        logger.info(OTHER_USER_EMAIL);
        logger.debug(userEmail);

        user.setEmail(userEmail);
    }

    @Override
    public void buyTicket(String movieName) {
        logger.debug(movieName);

        if (!user.isActive()) {
            try {
                throw new UserNotActiveException(NOT_ACTIVE_MESSAGE);
            } catch (UserNotActiveException e) {
                logger.error(e);
            }
        }

        Ticket ticket = null;
        try {
            ticket = ticketRepository.findByMovieName(movieName);
        } catch (NoFindMovieException e) {
            logger.error(e);
        }

        if (!TicketValidation.isAvailable(ticket)) {
            try {
                throw new UnavailableTicketException(UNAVAILABLE_TICKET_EXCEPTION);
            } catch (UnavailableTicketException e) {
                logger.error(e);
            }
        }

        try {
            if (!UserValidation.isEnoughCash(user, ticket.getPrice())) {
                throw new NoCashException(NO_CASH_EXCEPTION_MESSAGE);
            }
            user.addTicket(ticket);
            ticketRepository.delete(ticket);
            user.setBalance(user.getBalance() - ticket.getPrice());
        } catch (NoCashException e) {
            logger.error(e);
        }

        logger.info(TICKET_BUYING);
    }

    @Override
    public double checkTicketPrice(String movieName) {
        Ticket ticket = null;
        try {
            ticket = ticketRepository.findByMovieName(movieName);
        } catch (NoFindMovieException e) {
            logger.error(e);
        }
        if (ticket == null) {
            try {
                throw new NoFindMovieException(NO_FIND_MOVIE_EXCEPTION);
            } catch (NoFindMovieException e) {
                logger.error(e);
            }
        }

        logger.info(TICKET_PRICE);
        logger.debug(movieName);

        return ticket.getPrice();
    }

    @Override
    public List<Ticket> getAvailableTickets() {
        return ticketRepository.findAllAvailable();
    }

    @Override
    public List<Ticket> getAvailableForKidsTickets() {
        return ticketRepository.findAllAvailableTicketsForKids();
    }

    @Override
    public List<Ticket> getTicketsByMovieName(String movieName) {
        logger.info(MOVIE_NAME_ON_TICKET);
        logger.debug(movieName);

        return ticketRepository.findAllAvailable().stream()
                .filter(movie -> movie.getMovieName().equals(movieName))
                .collect(Collectors.toList());
    }

    @Override
    public void signIn(String userName) {
        logger.info(USER_ACTIONS_IN);
        logger.debug(userName);

        try {
            this.user = userRepository.findByUserName(userName).orElseThrow(() ->
                    new UserNotFoundException(NOT_FOUND_USERNAME_MESSAGE + userName));
        } catch (UserNotFoundException e) {
            logger.error(e);
        }
        user.setActive(true);
    }

    @Override
    public void signOut() {
        user.setActive(false);

        logger.info(USER_ACTIONS_OUT);
    }

    @Override
    public List<Ticket> getUsersTickets() {
        return user.getTickets();
    }
}

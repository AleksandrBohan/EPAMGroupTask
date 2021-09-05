package com.epam.jwd.service.validation;

import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.exception.IllegalAgeException;
import com.epam.jwd.service.exception.IllegalEmailException;
import com.epam.jwd.service.exception.IllegalNameSizeException;
import com.epam.jwd.service.exception.NoCashException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserValidation {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String NO_CASH_EXCEPTION_MESSAGE = "There is no money in your pocket to buy this ticket";
    private static final String ILLEGAL_NAME_SIZE_EXCEPTION_MESSAGE = "Name must be 1 or more symbols long";
    private static final String ILLEGAL_AGE_EXCEPTION_MESSAGE = "Age should be above 0";
    private static final String ILLEGAL_EMAIL_EXCEPTION_MESSAGE = "Enter valid email address";
    private static final int MAX_NAME_LENGTH = 30;
    private static final int MAX_AGE = 125;

    private static final Logger logger = LogManager.getLogger(UserValidation.class);

    private static final String CASH = "The cash is enough!";
    private static final String VALID_NAME = "The name is valid!";
    private static final String POSITIVE_AGE = "The age is positive!";
    private static final String EMAIL = "The email is valid!";
    private static final String INCORRECT_EMAIL = "Email was written wrong!";
    private static final String INCORRECT_AGE = "Age was written wrong!";
    private static final String INCORRECT_NAME = "Name was written wrong!";
    private static final String INCORRECT_CASH = "Name was written wrong!";

    public static boolean isEnoughCash(User user, double ticketCost) {
        logger.log(Level.DEBUG, ticketCost);

        if(user.getBalance() - ticketCost >= 0) {
            logger.log(Level.INFO, CASH);

            return true;
        }

        try {
            throw new NoCashException(NO_CASH_EXCEPTION_MESSAGE);
        } catch (NoCashException e) {
            logger.log(Level.ERROR, INCORRECT_CASH);
        }

        return false;
    public static boolean isEnoughCash(User user, double ticketCost)
            throws NoCashException {
        if(user.getBalance() - ticketCost < 0) {
            throw new NoCashException(NO_CASH_EXCEPTION_MESSAGE);

        }
        return true;
    }

    public static boolean isValidName(String name) throws IllegalNameSizeException {
            logger.log(Level.DEBUG, name);

        if(name.length() == 0) {
            logger.log(Level.ERROR, INCORRECT_NAME);//TODO

            throw new IllegalNameSizeException(ILLEGAL_NAME_SIZE_EXCEPTION_MESSAGE);
        }
        return true;
    }

    public static boolean isValidAge(int age) throws IllegalAgeException {
            logger.log(Level.DEBUG, age);

        if(age <= 0) {
            logger.log(Level.ERROR, INCORRECT_AGE);

            throw new IllegalAgeException(ILLEGAL_AGE_EXCEPTION_MESSAGE);
        }
        return true;
    }

    public static boolean isEmail(String email) throws IllegalEmailException {
        if(!email.matches(EMAIL_PATTERN)) {
            logger.log(Level.ERROR, INCORRECT_EMAIL);

            throw new IllegalEmailException(ILLEGAL_EMAIL_EXCEPTION_MESSAGE);
        }

            logger.log(Level.DEBUG, EMAIL);

        return true;
    }
}

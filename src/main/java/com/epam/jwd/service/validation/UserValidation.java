package com.epam.jwd.service.validation;

import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.exception.IllegalAgeException;
import com.epam.jwd.service.exception.IllegalEmailException;
import com.epam.jwd.service.exception.IllegalNameSizeException;
import com.epam.jwd.service.exception.NoCashException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserValidation {

    private static final Logger logger = LogManager.getLogger(UserValidation.class);

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String NO_CASH_EXCEPTION_MESSAGE = "There is no money in your pocket to buy this ticket";
    private static final String ILLEGAL_NAME_SIZE_EXCEPTION_MESSAGE = "Name must be 1 or more symbols long";
    private static final String ILLEGAL_AGE_EXCEPTION_MESSAGE = "Age should be above 0";
    private static final String ILLEGAL_EMAIL_EXCEPTION_MESSAGE = "Enter valid email address";
    private static final String CASH = "The cash is enough!";
    private static final String VALID_NAME = "The name is valid!";
    private static final String POSITIVE_AGE = "The age is positive!";
    private static final String EMAIL = "The email is valid!";
    private static final String INCORRECT_EMAIL = "Email was written wrong!";
    private static final String INCORRECT_AGE = "Age was written wrong!";
    private static final String INCORRECT_NAME = "Name was written wrong!";
    private static final String INCORRECT_CASH = "Cash is incorrect!";


    public static boolean isEnoughCash(User user, double ticketCost)
            throws NoCashException {
        logger.debug(ticketCost);
        logger.info(CASH);

        if (user.getBalance() - ticketCost < 0) {
            logger.error(INCORRECT_CASH);
            throw new NoCashException(NO_CASH_EXCEPTION_MESSAGE);

        }
        return true;
    }

    public static boolean isValidName(String name) {
        logger.debug(name);

        if (name.length() == 0) {
            logger.error(INCORRECT_NAME);

            try {
                throw new IllegalNameSizeException(ILLEGAL_NAME_SIZE_EXCEPTION_MESSAGE);
            } catch (IllegalNameSizeException e) {
                logger.error(e);
                return false;
            }
        }

        logger.info(VALID_NAME);

        return true;
    }

    public static boolean isValidAge(int age) {
        logger.debug(age);

        if (age <= 0) {
            logger.error(INCORRECT_AGE);

            try {
                throw new IllegalAgeException(ILLEGAL_AGE_EXCEPTION_MESSAGE);
            } catch (IllegalAgeException e) {
                logger.error(e);
            }
        }

        logger.info(POSITIVE_AGE);
        return true;
    }

    public static boolean isEmail(String email) {
        if (!email.matches(EMAIL_PATTERN)) {
            logger.info(INCORRECT_EMAIL);
            try {
                throw new IllegalEmailException(ILLEGAL_EMAIL_EXCEPTION_MESSAGE);
            } catch (IllegalEmailException e) {
                logger.error(e);
                return false;
            }
        }
        logger.debug(EMAIL);
        return true;
    }
}


package com.epam.jwd.controller;

import com.epam.jwd.repository.exception.UnavailableSaveTicketException;
import com.epam.jwd.service.api.SellerService;
import com.epam.jwd.service.impl.SellerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Controller {

    private static final Logger logger = LogManager.getLogger(Controller.class);

    private static final String APP_START = "App is running!";
    private static final String UNAVAILABLE_SAVE_TICKET_EXCEPTION_LOG_MESSAGE = "UnavailableSaveTicketException handled: ";

    public static void main(String[] args) {
        logger.info(APP_START);

        SellerService service = new SellerServiceImpl();
        try {
            service.createUSAMovieTicketList();
            service.createRussianMovieTicketList();
            service.createFranceMovieTicketList();
        } catch (UnavailableSaveTicketException exception) {
            logger.error(UNAVAILABLE_SAVE_TICKET_EXCEPTION_LOG_MESSAGE, exception);
        }
    }
}

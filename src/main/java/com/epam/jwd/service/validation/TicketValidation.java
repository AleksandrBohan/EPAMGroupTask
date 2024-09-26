package com.epam.jwd.service.validation;

import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.service.exception.UnavailableTicketException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TicketValidation {

    private static final Logger logger = LogManager.getLogger(TicketValidation.class);

    private static final String TICKET_UNAVAILABLE_EXCEPTION_MESSAGE = "These ticket isn't available, try any other";
    private static final String TICKET_ACCESS = "Ticket is available!";
    private static final String UNAVAILABLE_TICKET = "Ticket is available!";

    public static boolean isAvailable(Ticket ticket) {
        if (!ticket.isAvailable()) {
            logger.info(TICKET_ACCESS);

            try {
                throw new UnavailableTicketException(TICKET_UNAVAILABLE_EXCEPTION_MESSAGE);
            } catch (UnavailableTicketException e) {
                logger.error(UNAVAILABLE_TICKET, e);
            }
        }

        return false;
    }
}

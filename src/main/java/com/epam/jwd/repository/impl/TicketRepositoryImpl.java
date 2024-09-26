package com.epam.jwd.repository.impl;

import com.epam.jwd.repository.api.TicketRepository;
import com.epam.jwd.repository.exception.NoFindMovieException;
import com.epam.jwd.repository.exception.UnavailableSaveTicketException;
import com.epam.jwd.repository.model.Ticket;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketRepositoryImpl implements TicketRepository<Long, Ticket> {

    private static final Logger logger = LogManager.getLogger(TicketRepositoryImpl.class);

    private static final String CHECK_FOR_NULL = "Instance is null";
    private static final String SAVED_TICKET = "Ticket was saved!";
    private static final String ID_SORTING = "ID is searched";
    private static final String REMOVED_TICKET = "Ticket was remove!";
    private static final String ALL_AVAILABLE_TICKETS = "All available tickets are searched!";
    private static final String ALL_AVAILABLE_TICKETS_FOR_KIDS = "All available tickets for kids are searched!";
    private static final String MOVIE_NAME = "Movie was found by movie name";
    private static final String UNAVAILABLE_SAVE_TICKET_EXCEPTION = "Can not save the ticket";
    private static final String NO_FIND_MOVIE_EXCEPTION = "This film is not found";

    private static TicketRepositoryImpl instance;
    private final List<Ticket> ticketStorage = new ArrayList<>();

    private TicketRepositoryImpl() {
    }

    public static TicketRepositoryImpl getInstance() {
        if (instance == null) {
            logger.info(CHECK_FOR_NULL);

            instance = new TicketRepositoryImpl();
        }

        return instance;
    }

    @Override
    public void save(Ticket ticket) {
        logger.info(SAVED_TICKET);

        try {
            ticketStorage.add(ticket);
        } catch (Exception exception) {
            logger.error(exception);
            try {
                throw new UnavailableSaveTicketException(UNAVAILABLE_SAVE_TICKET_EXCEPTION + "( " + exception.getMessage() + " ).");
            } catch (UnavailableSaveTicketException e) {
                logger.error(UNAVAILABLE_SAVE_TICKET_EXCEPTION, e);
            }
        }
    }

    @Override
    public Ticket findById(Long id) {
        logger.info(ID_SORTING);
        logger.debug(id);

        return ticketStorage.stream()
                .filter(ticket -> id.equals(ticket.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean delete(Ticket ticket) {
        logger.info(REMOVED_TICKET);

        return ticketStorage.remove(ticket);
    }

    @Override
    public List<Ticket> findAllAvailable() {
        logger.info(ALL_AVAILABLE_TICKETS);

        return ticketStorage.stream()
                .filter(Ticket::isAvailable)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findAll() {
        return ticketStorage;
    }

    @Override
    public List<Ticket> findAllAvailableTicketsForKids() {
        logger.info(ALL_AVAILABLE_TICKETS_FOR_KIDS);

        return ticketStorage.stream()
                .filter(Ticket::isAvailableForKids)
                .collect(Collectors.toList());
    }

    @Override
    public Ticket findByMovieName(String movieName) {
        logger.info(MOVIE_NAME);
        logger.debug(movieName);

        Ticket ticket = ticketStorage.stream()
                .filter(tckt -> movieName.equals(tckt.getMovieName())
                        && tckt.isAvailable())
                .findFirst().orElse(null);
        if (ticket == null) {
            try {
                throw new NoFindMovieException(NO_FIND_MOVIE_EXCEPTION);
            } catch (NoFindMovieException e) {
                logger.error(e);
            }
        }
        return ticket;
    }
}

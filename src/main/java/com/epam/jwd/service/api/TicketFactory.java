package com.epam.jwd.service.api;

import com.epam.jwd.repository.model.Ticket;

public interface TicketFactory {

    Ticket createHorrorMovieTicket();

    Ticket createAdventureTicket();

    Ticket createActionMovieTicket();

    Ticket createMelodramaMovieTicket();

    Ticket createComedyMovieTicket();

    Ticket createDetectiveMovieTicket();
}

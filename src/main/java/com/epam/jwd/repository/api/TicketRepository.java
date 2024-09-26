package com.epam.jwd.repository.api;

import com.epam.jwd.repository.exception.NoFindMovieException;
import com.epam.jwd.repository.exception.UnavailableSaveTicketException;
import com.epam.jwd.repository.model.Entity;
import com.epam.jwd.repository.model.Ticket;

import java.util.List;

public interface TicketRepository<V, T extends Entity<V>> {

    void save(Ticket ticket) throws UnavailableSaveTicketException;

    T findById(V id);

    T findByMovieName(String movieName) throws NoFindMovieException;

    boolean delete(Ticket ticket);

    List<T> findAllAvailable();

    List<T> findAll();

    List<T> findAllAvailableTicketsForKids();
}

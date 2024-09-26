package com.epam.jwd.service.api;

import com.epam.jwd.repository.exception.UnavailableSaveTicketException;

public interface SellerService {

    void createUSAMovieTicketList() throws UnavailableSaveTicketException;

    void createFranceMovieTicketList() throws UnavailableSaveTicketException;

    void createRussianMovieTicketList() throws UnavailableSaveTicketException;
}

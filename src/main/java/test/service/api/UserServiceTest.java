package test.service.api;

import com.epam.jwd.repository.impl.TicketRepositoryImpl;
import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserServiceImpl service;
    private static List<User> userStorage;
    private static List<Ticket> ticketStorage;
    private User validUser;

    @BeforeAll
    static void init() {
        userStorage = UserRepositoryImpl.getInstance().findAll();
        ticketStorage = TicketRepositoryImpl.getInstance().findAll();
    }

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl();
        validUser = new User(0L, "Jack", 100, 20, "jack@mail.ru", true);
        service.registration(validUser);
    }

    @Test
    void shouldRegisterUserWhenTheUserIsValid() {
        User currentUser = userStorage.remove(0);
        assertSame(validUser, currentUser);
    }

    @Test
    void shouldBuyTicketWhenTheFilmExistsAndEnoughCash() {
        String filmName = "Titanic";
        double price = 14.00;
        double expectedCash = validUser.getBalance() - price;
        Ticket ticket =
                new Ticket(0L, filmName, "Melodrama", price, true, true);
        ticketStorage.add(ticket);
        service.buyTicket(filmName);
        assertEquals(0, ticketStorage.size());
        assertSame(ticket, validUser.getTickets().remove(0));
        assertEquals(expectedCash, validUser.getBalance(),
                "Money should decrease, when buying");
    }

    @Test
    void shouldThrowNullPointerExceptionWhenTheFilmDoesNotExist() {
        String filmName = "Titanic";
        assertThrows(NullPointerException.class,
                () -> service.buyTicket(filmName));
    }

    @Test
    void shouldNotBuyTicketWhenTheUserDoesNotHaveEnoughCash() {
        User poorUser = new User(0L, "Jack", 0, 20, "jack@mail.ru", true);
        service.registration(poorUser);
        String filmName = "Titanic";
        Ticket ticket =
                new Ticket(0L, filmName, "Melodrama", 14.00, true, true);
        ticketStorage.add(ticket);
        service.buyTicket(filmName);
        assertEquals(0, poorUser.getTickets().size(),
                "User should have no tickets");
        assertSame(ticket, ticketStorage.remove(0),
                "Ticket should stay in storage");
    }
}

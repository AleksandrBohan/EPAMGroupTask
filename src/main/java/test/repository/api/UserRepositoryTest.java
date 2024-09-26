package test.repository.api;

import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.repository.model.User;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private static UserRepositoryImpl repository;
    private static List<User> storage;
    private static final long ID = 0L;
    private static final String NAME = "Jack";
    private User user;

    @BeforeAll
    static void init() {
        repository = UserRepositoryImpl.getInstance();
        storage = repository.findAll();
    }

    @BeforeEach
    void setUp() {
        user = new User(ID, NAME, 100, 20, "jack@mail.ru", true);
        repository.save(user);
    }

    @AfterEach
    void tearDown() {
        storage.clear();
    }

    @Test
    void shouldFindUserByIdWhenIdExists() {
        assertTrue(repository.findById(ID).isPresent());
        assertSame(user, repository.findById(ID).get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenIdDoesNotExist() {
        storage.clear();
        assertSame(Optional.empty(), repository.findById(ID));
    }

    @Test
    void shouldFindUserByNameWhenNameExists() {
        assertTrue(repository.findByUserName(NAME).isPresent());
        assertSame(user, repository.findByUserName(NAME).get());
    }

    @Test
    void shouldReturnEmptyOptionalWhenNameDoesNotExist() {
        storage.clear();
        assertSame(Optional.empty(), repository.findByUserName(NAME));
    }
}

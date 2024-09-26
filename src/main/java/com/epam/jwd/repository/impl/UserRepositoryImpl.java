package com.epam.jwd.repository.impl;

import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.exception.UnavailableSaveUserException;
import com.epam.jwd.repository.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository<Long, User> {

    private static final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);

    private static final String CHECK_FOR_NULL = "Instance is null";
    private static final String SAVED_USER = "Ticket was saved!";
    private static final String ID_SORTING = "ID is searching";
    private static final String REMOVED_USER = "User was remove!";
    private static final String USERNAME_CHECK = "Username is searching!";
    private static final String USER_SEARCH = "User is being searched";
    private static final String UNAVAILABLE_SAVE_USER_EXCEPTION = "Can not save the user";

    private static UserRepositoryImpl instance;
    private final List<User> userStorage = new ArrayList<>();

    private UserRepositoryImpl() {
    }

    @Override
    public void save(User user) {
        logger.info(SAVED_USER);

        try {
            userStorage.add(user);
        } catch (Exception exception) {
            logger.error(exception);
            try {
                throw new UnavailableSaveUserException(UNAVAILABLE_SAVE_USER_EXCEPTION + "( " + exception.getMessage() + " ).");
            } catch (UnavailableSaveUserException e) {
                logger.error(e);
            }
        }
    }

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            logger.info(CHECK_FOR_NULL);

            instance = new UserRepositoryImpl();
        }

        return instance;
    }

    @Override
    public Optional<User> findById(Long id) {
        logger.info(ID_SORTING);
        logger.debug(id);

        return userStorage.stream()
                .filter(user -> id.equals(user.getId()))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return userStorage;
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        logger.info(USERNAME_CHECK);
        logger.debug(userName);

        return userStorage.stream()
                .filter(user -> userName.equals(user.getName()))
                .findFirst();
    }

    @Override
    public boolean delete(User user) {
        logger.info(REMOVED_USER);

        return userStorage.remove(user);
    }

    @Override
    public Optional<User> findUser(User user) {
        logger.info(USER_SEARCH);

        int index = userStorage.indexOf(user);
        if (index == -1) {
            return Optional.empty();
        }
        return Optional.of(userStorage.get(index));
    }
}

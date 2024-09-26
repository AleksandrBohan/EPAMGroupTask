package com.epam.jwd.repository.api;


import com.epam.jwd.repository.exception.UnavailableSaveUserException;
import com.epam.jwd.repository.model.Entity;

import java.util.List;
import java.util.Optional;

public interface UserRepository<V, T extends Entity<V>> {

    void save(T user) throws UnavailableSaveUserException;

    Optional<T> findById(V id);

    Optional<T> findUser(T user);

    List<T> findAll();

    Optional<T> findByUserName(String userName);

    boolean delete(T user);
}

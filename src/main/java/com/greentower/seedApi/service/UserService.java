package com.greentower.seedApi.service;

import com.greentower.seedApi.model.entity.User;
import com.greentower.seedApi.model.enums.UserStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User save(User user);

    User update(User user);

    void delete(User user);

    List<User> find(User userFiltered);

    List<User> findAll();

    User updateStatus(User user, UserStatus userStatus);

    void isValid(User user);

    Optional<User> findById(UUID id);
}

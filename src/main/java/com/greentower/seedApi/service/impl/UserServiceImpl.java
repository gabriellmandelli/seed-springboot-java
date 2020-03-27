package com.greentower.seedApi.service.impl;

import com.greentower.seedApi.exception.ValidationException;
import com.greentower.seedApi.model.entity.User;
import com.greentower.seedApi.model.enums.UserStatus;
import com.greentower.seedApi.model.repository.UserRepository;
import com.greentower.seedApi.service.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@ComponentScan
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        isValid(user);
        user.setStatus(UserStatus.ENABLED);
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        Objects.requireNonNull(user.getId());
        isValid(user);
        user.setUpdatedAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        Objects.requireNonNull(user.getId());
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public List<User> find(User userFiltered) {
        Example example = Example.of( userFiltered,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(StringMatcher.CONTAINING) );

        return userRepository.findAll(example);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User updateStatus(User user, UserStatus userStatus) {
        user.setStatus(userStatus);
        return update(user);
    }

    @Override
    public void isValid(User user) {

        if(user.getEmail() == null || user.getEmail().trim().equals("")) {
            throw new ValidationException("Informe um Email válido.");
        }

        if(user.getName() == null || user.getName().trim().equals("")) {
            throw new ValidationException("Informe um Nome válida.");
        }

        if(user.getUserName() == null || user.getUserName().trim().equals("")) {
            throw new ValidationException("Informe um UserName válido.");
        }

        if(user.getPassword() == null || user.getPassword().trim().equals("") ) {
            throw new ValidationException("Informe uma Senha válida.");
        }

        User userExist = new User();
        userExist.setUserName(user.getUserName());

        if (!find(userExist).isEmpty()){
            throw new ValidationException("Usuario informado já está sendo utilizado.");
        }

        userExist = new User();
        userExist.setEmail(user.getEmail());

        if (!find(userExist).isEmpty()){
            throw new ValidationException("Email informado já está sendo utilizado.");
        }
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }
}

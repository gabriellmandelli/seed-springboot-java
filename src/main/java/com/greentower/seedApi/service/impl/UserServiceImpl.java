package com.greentower.seedApi.service.impl;

import com.greentower.seedApi.util.exception.ValidationBadRequestException;
import com.greentower.seedApi.model.entity.User;
import com.greentower.seedApi.model.enums.UserStatus;
import com.greentower.seedApi.model.repository.UserRepository;
import com.greentower.seedApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        isValid(user);
        user.setId(UUID.randomUUID());
        user.setStatus(UserStatus.ENABLED);
        return userRepository.save(user);
    }

    @Override
    public User update(UUID id, User user) {
        return this.userRepository.findById(id)
                .map(existing -> {
                   user.setId(id);
                   return this.userRepository.save(user);
                }).orElseThrow();
    }

    @Override
    public void delete(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> find(User userFiltered) {
        Example example = Example.of(userFiltered,
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
        return this.update(user.getId(), user);
    }

    @Override
    public void isValid(User user) {

        if(user.getEmail() == null || user.getEmail().trim().equals("")) {
            throw new ValidationBadRequestException("Informe um Email válido.");
        }

        if(user.getName() == null || user.getName().trim().equals("")) {
            throw new ValidationBadRequestException("Informe um Nome válida.");
        }

        if(user.getUserName() == null || user.getUserName().trim().equals("")) {
            throw new ValidationBadRequestException("Informe um UserName válido.");
        }

        if(user.getPassword() == null || user.getPassword().trim().equals("") ) {
            throw new ValidationBadRequestException("Informe uma Senha válida.");
        }

        User userExist;
        List<User> listUser;

        userExist = new User();
        userExist.setUserName(user.getUserName());

        listUser = this.find(userExist);

        if (!listUser.isEmpty()){
            if (listUser.stream().findFirst().get().getUserName().equals(user.getUserName())) {
                throw new ValidationBadRequestException("Usuario informado já está sendo utilizado.");
            }
        }

        userExist = new User();
        userExist.setEmail(user.getEmail());

        listUser = this.find(userExist);

        if (!listUser.isEmpty()){
            if (listUser.stream().findFirst().get().getEmail().equals(user.getEmail())){
                throw new ValidationBadRequestException("Email informado já está sendo utilizado.");
            }
        }
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }
}

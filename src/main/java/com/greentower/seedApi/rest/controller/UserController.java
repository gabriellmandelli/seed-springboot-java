package com.greentower.seedApi.rest.controller;

import com.greentower.seedApi.rest.dto.UserDTO;
import com.greentower.seedApi.model.entity.User;

import com.greentower.seedApi.service.UserService;

import com.greentower.seedApi.util.exception.ResponseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UserDTO>> find(){
        return  ResponseEntity.ok(UserDTO.converter(userService.findAll()));
    }

    @GetMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO findById(@PathVariable("userId")UUID userId) {
        User user = userService.findById(userId);

        return new UserDTO(user);
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDTO save(@RequestBody User user){
        return new UserDTO(userService.save(user));
    }

    @PutMapping(value = "{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDTO update(@PathVariable("userId") UUID userId, @RequestBody User user) {
        return new UserDTO(userService.update(userId, user));
    }

    @DeleteMapping(value = "{userId}")
    public void delete(@PathVariable("userId") UUID userId){
        userService.delete(userId);
    }
}

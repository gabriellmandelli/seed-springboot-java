package com.greentower.seedApi.controller;

import com.greentower.seedApi.controller.dto.UserDTO;
import com.greentower.seedApi.model.entity.User;

import com.greentower.seedApi.service.UserService;

import org.hibernate.service.spi.InjectService;
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
    public ResponseEntity find(){
        List<UserDTO> userDTOList = UserDTO.converter(userService.findAll());
        return  ResponseEntity.ok(userDTOList);
    }

    @GetMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findById(@PathVariable("userId")UUID userId) {
        Optional<User> user = userService.findById(userId);

        if (user.isEmpty()){
            return ResponseEntity.ok(ResponseEntity.notFound().build());
        }else{
            return ResponseEntity.ok(new UserDTO(user.get()));
        }
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity save(@RequestBody User user){
        return ResponseEntity.ok(new UserDTO(userService.save(user)));
    }

    @PutMapping(value = "{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity update(@PathVariable("userId") UUID userId, @RequestBody User user) {
        return ResponseEntity.ok(new UserDTO(userService.update(userId, user)));
    }

    @DeleteMapping(value = "{userId}")
    public void delete(@PathVariable("userId") UUID userId){
        userService.delete(userId);
    }
}

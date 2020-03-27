package com.greentower.seedApi.controller.dto;

import com.greentower.seedApi.model.entity.User;
import com.greentower.seedApi.model.enums.UserStatus;
import com.greentower.seedApi.util.BaseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO extends BaseDTO {

    private String name;
    private String email;
    private String phone;
    private UserStatus status;

    public UserDTO(User user){
        this.setId(user.getId());
        this.setCreatedAt(user.getCreatedAt());
        this.setUpdatedAt(user.getUpdatedAt());
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.status = user.getStatus();
    }

    public static List<UserDTO> converter (List<User> userList){
        return userList.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}

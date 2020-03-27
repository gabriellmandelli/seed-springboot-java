package com.greentower.seedApi.controller.dto;

import com.greentower.seedApi.model.entity.User;
import com.greentower.seedApi.model.enums.UserStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserDTO {

    private UUID id;
    private Date createdAt;
    private Date updatedAt;
    private String name;
    private String email;
    private String phone;
    private UserStatus status;

    public UserDTO(User user){
        this.id = user.getId();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.status = user.getStatus();
    }

    public static List<UserDTO> converter (List<User> userList){
        return userList.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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

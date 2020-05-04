package com.greentower.seedApi.model.entity;

import com.greentower.seedApi.rest.dto.UserDTO;
import com.greentower.seedApi.util.generic.BaseEntity;
import com.greentower.seedApi.model.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "auth_user")
public class User extends BaseEntity implements Serializable {

    @Column(name = "name")
    @Size(min = 1, max = 255, message = "Field name need be 1 between 255 carcters")
    @NotEmpty(message = "Field name is not empty")
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    @Size(min = 1, max = 10, message = "Field userName need be 1 between 10 carcters")
    @NotEmpty(message = "Field userName is not empty")
    private String userName;

    @Column(name = "password")
    @Size(min = 8, message = "Field password need be 8 carcters")
    @NotEmpty(message = "Field password is not empty")
    private String password;

    @Email(message = "Email is not valid")
    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty(message = "Field email is not empty")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    @Enumerated(value = EnumType.ORDINAL)
    private UserStatus status;

    public User(UserDTO userDTO) {
        this.setId(userDTO.getId());
        this.setName(userDTO.getName());
        this.setUserName(userDTO.getUserName());
        this.setPassword(userDTO.getPassword());
        this.setEmail(userDTO.getEmail());
        this.setPhone(userDTO.getPhone());
        this.setStatus(userDTO.getStatus());
    }
}

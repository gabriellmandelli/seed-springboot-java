package com.greentower.seedApi.rest.dto;

import com.greentower.seedApi.model.entity.User;
import com.greentower.seedApi.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {
    private UUID id;
    private String name;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private UserStatus status;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.status = user.getStatus();
    }

    public static List<UserDTO> converter (List<User> userList){
        return userList.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public static List<User> converterDto (List<UserDTO> userDTOList){
        return userDTOList.stream().map(User::new).collect(Collectors.toList());
    }
}

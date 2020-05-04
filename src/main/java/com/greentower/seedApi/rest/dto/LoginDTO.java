package com.greentower.seedApi.rest.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {
    private String userName;
    private String password;
}

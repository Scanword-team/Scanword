package ru.scanword.security;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String username;
    private String password;
}

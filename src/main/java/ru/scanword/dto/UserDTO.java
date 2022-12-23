package ru.scanword.dto;

import lombok.Data;
import ru.scanword.domain.enums.Role;

@Data
public class UserDTO {

    private long id;
    private String name;
    private String password;
    private Role role;}

package ru.scanword.dto;

import lombok.Data;
import ru.scanword.domain.enums.Role;
import ru.scanword.domain.enums.Status;

@Data
public class UserDTO {

    private long id;
    private String name;
    private String password;
    private Role role;
    private Status status;
}

package ru.scanword.dto;

import lombok.Data;
import ru.scanword.domain.enums.Role;

@Data
public class UserDTO {

    private long id;
    private int width;
    private int height;
    private Role role;
}

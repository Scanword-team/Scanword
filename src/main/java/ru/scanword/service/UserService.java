package ru.scanword.service;

import ru.scanword.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAll();
    UserDTO getById(Long id);
    UserDTO create(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    boolean deleteById(Long id);

}

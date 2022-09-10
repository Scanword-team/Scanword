package ru.scanword.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.scanword.dto.UserDTO;
import ru.scanword.service.impl.UserServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("getById/{id}")
    public UserDTO getById(@PathVariable Long id) {
        if (id != null) {
            return userService.getById(id);
        }
        return null;
    }

    @PostMapping("/create")
    UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PostMapping("/update")
    UserDTO update(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Long id){
        return userService.deleteById(id);
    }
}

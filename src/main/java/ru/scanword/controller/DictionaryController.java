package ru.scanword.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.scanword.dto.DictionaryDTO;
import ru.scanword.dto.UserDTO;
import ru.scanword.service.impl.DictionaryServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dict")
public class DictionaryController {

    private final DictionaryServiceImpl dictionaryService;

    @GetMapping
    public List<DictionaryDTO> getAll() {
        return dictionaryService.getAll();
    }

    @PostMapping("/create")
    DictionaryDTO create(@RequestBody DictionaryDTO userDTO) {
        return dictionaryService.create(userDTO);
    }
}

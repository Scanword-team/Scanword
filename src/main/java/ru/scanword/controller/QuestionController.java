package ru.scanword.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.scanword.dto.QuestionDTO;
import ru.scanword.service.impl.QuestionServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionServiceImpl questionService;

    @GetMapping
    public List<QuestionDTO> getAll() {
        return questionService.getAll();
    }

    @GetMapping("getByDictId/{id}")
    public List<QuestionDTO> getAllByDictionaryId(@PathVariable Long id) {
        return questionService.getAllByDictionaryId(id);
    }

    @GetMapping("getById/{id}")
    public QuestionDTO getById(@PathVariable Long id) {
        if (id != null) {
            return questionService.getById(id);
        }
        return null;
    }

    @PostMapping("/create")
    QuestionDTO create(@RequestBody QuestionDTO questionDTO) {
        return questionService.create(questionDTO);
    }

    @PostMapping("/update")
    QuestionDTO update(@RequestBody QuestionDTO questionDTO) {
        return questionService.update(questionDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Long id){
        return questionService.deleteById(id);
    }
}

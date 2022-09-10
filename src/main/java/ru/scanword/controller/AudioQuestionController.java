package ru.scanword.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.scanword.dto.AudioQuestionDTO;
import ru.scanword.dto.QuestionDTO;
import ru.scanword.service.impl.AudioQuestionServiceImpl;
import ru.scanword.service.impl.QuestionServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/audioQuestion")
public class AudioQuestionController {

    private final AudioQuestionServiceImpl audioQuestionService;

    @GetMapping
    public List<AudioQuestionDTO> getAll() {
        return audioQuestionService.getAll();
    }

    @GetMapping("getById/{id}")
    public AudioQuestionDTO getById(@PathVariable Long id) {
        if (id != null) {
            return audioQuestionService.getById(id);
        }
        return null;
    }

    @PostMapping("/create")
    AudioQuestionDTO create(@RequestBody AudioQuestionDTO audioQuestionDTO) {
        return audioQuestionService.create(audioQuestionDTO);
    }

    @PostMapping("/update")
    AudioQuestionDTO update(@RequestBody AudioQuestionDTO audioQuestionDTO) {
        return audioQuestionService.update(audioQuestionDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Long id){
        return audioQuestionService.deleteById(id);
    }
}

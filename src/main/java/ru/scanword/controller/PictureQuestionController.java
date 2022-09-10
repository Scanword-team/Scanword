package ru.scanword.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.scanword.dto.AudioQuestionDTO;
import ru.scanword.dto.PictureQuestionDTO;
import ru.scanword.service.impl.AudioQuestionServiceImpl;
import ru.scanword.service.impl.PictureQuestionServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pictureQuestion")
public class PictureQuestionController {

    private final PictureQuestionServiceImpl pictureQuestionService;

    @GetMapping
    public List<PictureQuestionDTO> getAll() {
        return pictureQuestionService.getAll();
    }

    @GetMapping("getById/{id}")
    public PictureQuestionDTO getById(@PathVariable Long id) {
        if (id != null) {
            return pictureQuestionService.getById(id);
        }
        return null;
    }

    @PostMapping("/create")
    PictureQuestionDTO create(@RequestBody PictureQuestionDTO pictureQuestionDTO) {
        return pictureQuestionService.create(pictureQuestionDTO);
    }

    @PostMapping("/update")
    PictureQuestionDTO update(@RequestBody PictureQuestionDTO pictureQuestionDTO) {
        return pictureQuestionService.update(pictureQuestionDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Long id){
        return pictureQuestionService.deleteById(id);
    }
}

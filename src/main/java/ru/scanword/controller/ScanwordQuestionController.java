package ru.scanword.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.scanword.dto.ScanwordQuestionDTO;
import ru.scanword.service.impl.ScanwordQuestionServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scanwordQuestion")
public class ScanwordQuestionController {

    private final ScanwordQuestionServiceImpl scanwordQuestionService;

    @GetMapping
    public List<ScanwordQuestionDTO> getAll() {
        return scanwordQuestionService.getAll();
    }

    @GetMapping("getById/{scanword_id}/{question_id}")
    public ScanwordQuestionDTO getById(@PathVariable Long scanword_id, @PathVariable Long question_id) {
        if (scanword_id != null && question_id != null) {
            return scanwordQuestionService.getById(scanword_id, question_id);
        }
        return null;
    }

    @GetMapping("getAllByScanwordId/{scanword_id}")
    public List<ScanwordQuestionDTO> getAllByScanwordId(@PathVariable Long scanword_id) {
        return scanwordQuestionService.getAllByScanwordId(scanword_id);
    }


    @PostMapping("/create")
    ScanwordQuestionDTO create(@RequestBody ScanwordQuestionDTO scanwordQuestionDTO) {
        return scanwordQuestionService.create(scanwordQuestionDTO);
    }

    @PostMapping("/update")
    ScanwordQuestionDTO update(@RequestBody ScanwordQuestionDTO scanwordQuestionDTO) {
        return scanwordQuestionService.update(scanwordQuestionDTO);
    }

    @DeleteMapping("/deleteById/{scanword_id}/{question_id}")
    public boolean deleteById(@PathVariable Long scanword_id, @PathVariable Long question_id){
        return scanwordQuestionService.deleteById(scanword_id, question_id);
    }
}

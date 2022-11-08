package ru.scanword.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.scanword.dto.QuestionDTO;
import ru.scanword.dto.SolvableScanwordDTO;
import ru.scanword.service.impl.SolvableScanwordServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/solvableScanword")
public class SolvableScanwordController {

    private final SolvableScanwordServiceImpl solvableScanwordService;

    @GetMapping
    public List<SolvableScanwordDTO> getAll() {
        return solvableScanwordService.getAll();
    }

    @GetMapping("getById/{id}")
    public SolvableScanwordDTO getById(@PathVariable Long id) {
        if (id != null) {
            return solvableScanwordService.getById(id);
        }
        return null;
    }

    @PostMapping("/create")
    SolvableScanwordDTO create(@RequestBody SolvableScanwordDTO solvableScanwordDTO) {
        return solvableScanwordService.create(solvableScanwordDTO);
    }

    @PostMapping("/update")
    SolvableScanwordDTO update(@RequestBody SolvableScanwordDTO solvableScanwordDTO) {
        return solvableScanwordService.update(solvableScanwordDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Long id){
        return solvableScanwordService.deleteById(id);
    }

    @GetMapping("getAllByScanwordId/{scanwordId}")
    public List<QuestionDTO> getAll(@PathVariable Long scanwordId) {
        return solvableScanwordService.getAllByScanwordId(scanwordId);
    }

    @PostMapping("/addResolvedQuestion/{scanwordId}")
    public QuestionDTO addResolvedQuestion(@PathVariable Long scanwordId, @RequestBody QuestionDTO questionDTO) {
        return solvableScanwordService.addResolvedQuestion(scanwordId, questionDTO);
    }
}

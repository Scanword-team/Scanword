package ru.scanword.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.scanword.dto.QuestionDTO;
import ru.scanword.dto.ScanwordDTO;
import ru.scanword.dto.StatsDTO;
import ru.scanword.service.impl.QuestionServiceImpl;
import ru.scanword.service.impl.ScanwordServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scanword")
public class ScanwordController {

    private final ScanwordServiceImpl scanwordService;

    @GetMapping
    public List<ScanwordDTO> getAll() {
        return scanwordService.getAll();
    }

    @GetMapping("getById/{id}")
    public ScanwordDTO getById(@PathVariable Long id) {
        if (id != null) {
            return scanwordService.getById(id);
        }
        return null;
    }

    @PostMapping("/create")
    ScanwordDTO create(@RequestBody ScanwordDTO scanwordDTO) {
        return scanwordService.create(scanwordDTO);
    }

    @PostMapping("/update")
    ScanwordDTO update(@RequestBody ScanwordDTO scanwordDTO) {
        return scanwordService.update(scanwordDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Long id){
        return scanwordService.deleteById(id);
    }

    @GetMapping("getStats/{id}")
    public StatsDTO getStats(@PathVariable Long id) {
        return scanwordService.getStats(id);
    }
}

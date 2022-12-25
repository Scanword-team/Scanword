package ru.scanword.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.scanword.dto.AudioDTO;
import ru.scanword.dto.DictionaryDTO;
import ru.scanword.dto.QuestionDTO;
import ru.scanword.service.impl.AudioServiceImpl;
import ru.scanword.service.impl.DictionaryServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/audio")
public class AudioController {

    private final AudioServiceImpl audioService;

    @GetMapping
    public List<AudioDTO> getAll() {
        return audioService.getAll();
    }

    @GetMapping("/used")
    public List<AudioDTO> getAllUsed() {
        return audioService.getAllUsed();
    }


    @PostMapping("/create")
    AudioDTO create(@RequestBody AudioDTO audioDTO) {
        return audioService.create(audioDTO);
    }

    @PostMapping("/saveAll")
    boolean create(@RequestBody List<AudioDTO> audioDTO) {
        return audioService.saveAll(audioDTO);
    }

    @GetMapping("getById/{id}")
    public AudioDTO getById(@PathVariable Long id) {
        if (id != null) {
            return audioService.getById(id);
        }
        return null;
    }

    @PostMapping("/update")
    AudioDTO update(@RequestBody AudioDTO audioDTO) {
        return audioService.update(audioDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return audioService.deleteById(id);
    }
}

package ru.scanword.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.scanword.dto.ImageDTO;
import ru.scanword.service.impl.ImageServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class ImageController {


    private final ImageServiceImpl imageService;

    @GetMapping
    public List<ImageDTO> getAll() {
        return imageService.getAll();
    }

    @PostMapping("/create")
    ImageDTO create(@RequestBody ImageDTO imageDTO) {
        return imageService.create(imageDTO);
    }

    @PostMapping("/saveAll")
    boolean saveAll(@RequestBody List<ImageDTO> imageDTOList) {
        return imageService.saveAll(imageDTOList);
    }

    @GetMapping("getById/{id}")
    public ImageDTO getById(@PathVariable Long id) {
        if (id != null) {
            return imageService.getById(id);
        }
        return null;
    }

    @PostMapping("/update")
    ImageDTO update(@RequestBody ImageDTO imageDTO) {
        return imageService.update(imageDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return imageService.deleteById(id);
    }
}

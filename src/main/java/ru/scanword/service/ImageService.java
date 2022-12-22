package ru.scanword.service;

import ru.scanword.dto.AudioDTO;
import ru.scanword.dto.ImageDTO;

import java.util.List;

public interface ImageService {

    List<ImageDTO> getAll();

    ImageDTO getById(Long id);

    ImageDTO create(ImageDTO questionDTO);

    ImageDTO update(ImageDTO questionDTO);

    boolean deleteById(Long id);
}

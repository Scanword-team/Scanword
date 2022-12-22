package ru.scanword.service;

import ru.scanword.dto.AudioDTO;
import ru.scanword.dto.QuestionDTO;

import java.util.List;

public interface AudioService {

    List<AudioDTO> getAll();

    AudioDTO getById(Long id);

    AudioDTO create(AudioDTO audioDTO);

    AudioDTO update(AudioDTO audioDTO);

    boolean deleteById(Long id);
}

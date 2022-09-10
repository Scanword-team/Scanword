package ru.scanword.service;

import ru.scanword.dto.AudioQuestionDTO;

import java.util.List;

public interface AudioQuestionService {

    List<AudioQuestionDTO> getAll();
    AudioQuestionDTO getById(Long id);
    AudioQuestionDTO create(AudioQuestionDTO audioQuestionDTO);
    AudioQuestionDTO update(AudioQuestionDTO audioQuestionDTO);
    boolean deleteById(Long id);

}

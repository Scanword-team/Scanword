package ru.scanword.service;

import ru.scanword.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {

    List<QuestionDTO> getAll();
    QuestionDTO getById(Long id);
    QuestionDTO create(QuestionDTO questionDTO);
    QuestionDTO update(QuestionDTO questionDTO);
    boolean deleteById(Long id);

}

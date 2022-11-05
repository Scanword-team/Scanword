package ru.scanword.service;

import ru.scanword.dto.ScanwordQuestionDTO;

import java.util.List;

public interface ScanwordQuestionService {

    List<ScanwordQuestionDTO> getAll();
    ScanwordQuestionDTO getById(Long scanword_id, Long question_id);
    List<ScanwordQuestionDTO> getAllByScanwordId(Long scanword_id);
    ScanwordQuestionDTO create(ScanwordQuestionDTO scanwordQuestionDTO);
    ScanwordQuestionDTO update(ScanwordQuestionDTO scanwordQuestionDTO);
    boolean deleteById(Long scanword_id, Long question_id);


}

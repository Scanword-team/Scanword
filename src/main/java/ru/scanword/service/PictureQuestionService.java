package ru.scanword.service;

import ru.scanword.dto.PictureQuestionDTO;

import java.util.List;

public interface PictureQuestionService {

    List<PictureQuestionDTO> getAll();
    PictureQuestionDTO getById(Long id);
    PictureQuestionDTO create(PictureQuestionDTO pictureQuestionDTO);
    PictureQuestionDTO update(PictureQuestionDTO pictureQuestionDTO);
    boolean deleteById(Long id);

}

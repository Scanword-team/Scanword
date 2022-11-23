package ru.scanword.service;

import ru.scanword.domain.Question;
import ru.scanword.dto.QuestionDTO;
import ru.scanword.dto.ScanwordDTO;
import ru.scanword.dto.SolvableScanwordDTO;

import java.util.List;

public interface SolvableScawordService {

    List<SolvableScanwordDTO> getAll();
    SolvableScanwordDTO getById(Long id);
    SolvableScanwordDTO create(SolvableScanwordDTO solvableScanwordDTO);
    SolvableScanwordDTO update(SolvableScanwordDTO solvableScanwordDTO);
    boolean deleteById(Long id);

    SolvableScanwordDTO createByScanwordId(Long scanwordId);
    List<QuestionDTO> getAllByScanwordId(Long scanwordId);
    QuestionDTO addResolvedQuestion(Long scanwordId, QuestionDTO questionDTO);
    List<Question> updateResoledQuestionList(Long scanwordId, List<Question> questionList);

    SolvableScanwordDTO decreasePromptById(Long id);
    SolvableScanwordDTO getByScanwordId(Long scanwordId);

}

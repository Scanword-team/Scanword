package ru.scanword.service;

import ru.scanword.dto.QuestionDTO;
import ru.scanword.dto.SolvableScanwordDTO;

import java.util.List;

public interface SolvableScawordService {

    List<SolvableScanwordDTO> getAll();
    SolvableScanwordDTO getById(Long id);
    SolvableScanwordDTO create(SolvableScanwordDTO solvableScanwordDTO);
    SolvableScanwordDTO update(SolvableScanwordDTO solvableScanwordDTO);
    boolean deleteById(Long id);
    List<QuestionDTO> getAllByScanwordId(Long scanwordId);


}

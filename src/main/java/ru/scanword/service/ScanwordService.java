package ru.scanword.service;

import ru.scanword.dto.ScanwordDTO;

import java.util.List;

public interface ScanwordService {

    List<ScanwordDTO> getAll();
    ScanwordDTO getById(Long id);
    ScanwordDTO create(ScanwordDTO scanwordDTO);
    ScanwordDTO update(ScanwordDTO scanwordDTO);
    boolean deleteById(Long id);

}

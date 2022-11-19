package ru.scanword.service;

import ru.scanword.dto.ScanwordDTO;
import ru.scanword.dto.StatsDTO;

import java.util.List;

public interface ScanwordService {

    List<ScanwordDTO> getAll();
    ScanwordDTO getById(Long id);
    ScanwordDTO create(ScanwordDTO scanwordDTO);
    ScanwordDTO update(ScanwordDTO scanwordDTO);
    boolean deleteById(Long id);

    StatsDTO getStats(Long id);


}

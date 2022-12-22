package ru.scanword.service;

import ru.scanword.dto.DictionaryDTO;

import java.util.List;

public interface DictionaryService {

    List<DictionaryDTO> getAll();

    DictionaryDTO getById(Long id);

    DictionaryDTO create(DictionaryDTO dictionaryDTO);
}

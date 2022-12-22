package ru.scanword.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scanword.domain.Dictionary;
import ru.scanword.dto.DictionaryDTO;
import ru.scanword.mapper.DictionaryMapper;
import ru.scanword.repository.DictionaryRepository;
import ru.scanword.service.DictionaryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryRepository dictionaryRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public List<DictionaryDTO> getAll() {
        return allToDTO(dictionaryRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public DictionaryDTO getById(Long id) {
        return toDTO(dictionaryRepository.getById(id));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public DictionaryDTO create(DictionaryDTO dictionaryDTO) {
        dictionaryRepository.save(toEntity(dictionaryDTO));
        return dictionaryDTO;
    }

    private Dictionary toEntity(DictionaryDTO dictionaryDTO) {
        return DictionaryMapper.DICTIONARY_MAPPER.toEntity(dictionaryDTO);
    }

    private DictionaryDTO toDTO(Dictionary dictionary) {
        return DictionaryMapper.DICTIONARY_MAPPER.toDTO(dictionary);
    }

    private List<Dictionary> allToEntity(List<DictionaryDTO> dictionaryDTOList) {
        return DictionaryMapper.DICTIONARY_MAPPER.allToEntity(dictionaryDTOList);
    }

    private List<DictionaryDTO> allToDTO(List<Dictionary> dictionaryList) {
        return DictionaryMapper.DICTIONARY_MAPPER.allToDTO(dictionaryList);
    }
}

package ru.scanword.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scanword.domain.SolvableScanword;
import ru.scanword.dto.SolvableScanwordDTO;
import ru.scanword.exceptions.ResourceNotFoundException;
import ru.scanword.mapper.SolvableScanwordMapper;
import ru.scanword.repository.SolvableScanwordRepository;
import ru.scanword.service.SolvableScawordService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolvableScanwordServiceImpl implements SolvableScawordService {

    private final SolvableScanwordRepository solvableScanwordRepository;

    //private final SolvableScanwordServiceImpl solvableScanwordService;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public List<SolvableScanwordDTO> getAll() {
        return allToDTO(solvableScanwordRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public SolvableScanwordDTO getById(Long id) {
        if (!(solvableScanwordRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("SolvableScanword with id = " + id + " not found", "");
        }
        return toDTO(solvableScanwordRepository.getById(id));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public SolvableScanwordDTO create(SolvableScanwordDTO solvableScanwordDTO) {
        solvableScanwordRepository.save(toEntity(solvableScanwordDTO));
        return solvableScanwordDTO;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public SolvableScanwordDTO update(SolvableScanwordDTO solvableScanwordDTO) {
        if(!(solvableScanwordRepository.findById(solvableScanwordDTO.getId()).isPresent())) {
            throw new ResourceNotFoundException("SolvableScanword with id = " + solvableScanwordDTO.getId() + " not found", "");
        }
        return create(solvableScanwordDTO);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public boolean deleteById(Long id) {
        if (!(solvableScanwordRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("SolvableScanword with id = " + id + " not found", "");
        }
        solvableScanwordRepository.deleteById(id);
        return (solvableScanwordRepository.findById(id).isPresent());
    }

    private SolvableScanword toEntity(SolvableScanwordDTO solvableScanwordDTO){
        return SolvableScanwordMapper.SOLVABLE_SCANWORD_MAPPER.toEntity(solvableScanwordDTO);
    }

    private SolvableScanwordDTO toDTO(SolvableScanword solvableScanword){
        return SolvableScanwordMapper.SOLVABLE_SCANWORD_MAPPER.toDTO(solvableScanword);
    }

    private List<SolvableScanword> allToEntity(List<SolvableScanwordDTO> solvableScanwordDTOList){
        return SolvableScanwordMapper.SOLVABLE_SCANWORD_MAPPER.allToEntity(solvableScanwordDTOList);
    }

    private List<SolvableScanwordDTO> allToDTO(List<SolvableScanword> solvableScanwordList){
        return SolvableScanwordMapper.SOLVABLE_SCANWORD_MAPPER.allToDTO(solvableScanwordList);
    }
}

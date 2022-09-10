package ru.scanword.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scanword.domain.Scanword;
import ru.scanword.dto.ScanwordDTO;
import ru.scanword.exceptions.ResourceNotFoundException;
import ru.scanword.mapper.ScanwordMapper;
import ru.scanword.repository.ScanwordRepository;
import ru.scanword.service.ScanwordService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ScanwordServiceImpl  implements ScanwordService {

    private final ScanwordRepository scanwordRepository;

    //private final ScanwordServiceImpl scanwordService;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public List<ScanwordDTO> getAll() {
        return allToDTO(scanwordRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public ScanwordDTO getById(Long id) {
        if (!(scanwordRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("Scanword with id = " + id + " not found", "");
        }
        return toDTO(scanwordRepository.getById(id));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public ScanwordDTO create(ScanwordDTO scanwordDTO) {
        scanwordRepository.save(toEntity(scanwordDTO));
        return scanwordDTO;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public ScanwordDTO update(ScanwordDTO scanwordDTO) {
        if(!(scanwordRepository.findById(scanwordDTO.getId()).isPresent())) {
            throw new ResourceNotFoundException("Scanword with id = " + scanwordDTO.getId() + " not found", "");
        }
        return create(scanwordDTO);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public boolean deleteById(Long id) {
        if (!(scanwordRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("Scanword with id = " + id + " not found", "");
        }
        scanwordRepository.deleteById(id);
        return (scanwordRepository.findById(id).isPresent());
    }

    private Scanword toEntity(ScanwordDTO connectionDTO){
        return ScanwordMapper.SCANWORD_MAPPER.toEntity(connectionDTO);
    }

    private ScanwordDTO toDTO(Scanword scanword){
        return ScanwordMapper.SCANWORD_MAPPER.toDTO(scanword);
    }

    private List<Scanword> allToEntity(List<ScanwordDTO> connectionDTOList){
        return ScanwordMapper.SCANWORD_MAPPER.allToEntity(connectionDTOList);
    }

    private List<ScanwordDTO> allToDTO(List<Scanword> connectionList){
        return ScanwordMapper.SCANWORD_MAPPER.allToDTO(connectionList);
    }
}

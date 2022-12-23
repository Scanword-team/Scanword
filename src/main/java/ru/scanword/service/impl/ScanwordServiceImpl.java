package ru.scanword.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scanword.domain.Scanword;
import ru.scanword.domain.User;
import ru.scanword.dto.ScanwordDTO;
import ru.scanword.dto.StatsDTO;
import ru.scanword.exceptions.ResourceNotFoundException;
import ru.scanword.mapper.ScanwordMapper;
import ru.scanword.repository.ScanwordQuestionRepository;
import ru.scanword.repository.ScanwordRepository;
import ru.scanword.repository.SolvableScanwordRepository;
import ru.scanword.repository.UserRepository;
import ru.scanword.service.ScanwordService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ScanwordServiceImpl  implements ScanwordService {

    private final ScanwordRepository scanwordRepository;
    private final ScanwordQuestionRepository scanwordQuestionRepository;
    private final UserRepository userRepository;
    private final SolvableScanwordRepository solvableScanwordRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public List<ScanwordDTO> getAll() {
        return allToDTO(scanwordRepository.findAll());
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public List<ScanwordDTO> getAllWithQuestions() {
        List<Scanword> scanwords = scanwordRepository.findAll();
        scanwords.removeIf(s -> scanwordQuestionRepository.findAllByScanword(s).isEmpty());
        return allToDTO(scanwords);
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
    @PreAuthorize("hasAuthority('create')")
    public ScanwordDTO create(ScanwordDTO scanwordDTO) {
        Scanword scanword = scanwordRepository.save(toEntity(scanwordDTO));
        return toDTO(scanword);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public ScanwordDTO update(ScanwordDTO scanwordDTO) {
        if(!(scanwordRepository.findById(scanwordDTO.getId()).isPresent())) {
            throw new ResourceNotFoundException("Scanword with id = " + scanwordDTO.getId() + " not found", "");
        }
        return create(scanwordDTO);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public boolean deleteById(Long id) {
        if (!(scanwordRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("Scanword with id = " + id + " not found", "");
        }
        scanwordRepository.deleteById(id);
        return (scanwordRepository.findById(id).isPresent());
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('read')")
    public StatsDTO getStats(Long id) {
        int total = scanwordQuestionRepository.findAllByScanwordId(id).size();
        UserDetails securityUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByName(securityUser.getUsername()).get();

        if (!solvableScanwordRepository.findByScanwordIdAndOwner(id, user).isPresent()) {
            return new StatsDTO(0,total);
        }
        int resolved = solvableScanwordRepository.findByScanwordIdAndOwner(id, user).get().getSolvedQuestions().size();
        return new StatsDTO(resolved, total);
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

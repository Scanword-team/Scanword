package ru.scanword.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scanword.domain.Question;
import ru.scanword.domain.Scanword;
import ru.scanword.domain.SolvableScanword;
import ru.scanword.domain.User;
import ru.scanword.dto.QuestionDTO;
import ru.scanword.dto.ScanwordDTO;
import ru.scanword.dto.SolvableScanwordDTO;
import ru.scanword.exceptions.ResourceNotFoundException;
import ru.scanword.mapper.QuestionMapper;
import ru.scanword.mapper.SolvableScanwordMapper;
import ru.scanword.repository.ScanwordRepository;
import ru.scanword.repository.SolvableScanwordRepository;
import ru.scanword.repository.UserRepository;
import ru.scanword.service.SolvableScawordService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolvableScanwordServiceImpl implements SolvableScawordService {

    private final SolvableScanwordRepository solvableScanwordRepository;
    private final UserRepository userRepository;
    private final ScanwordRepository scanwordRepository;


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
    @PreAuthorize("hasAuthority('save')")
    public SolvableScanwordDTO create(SolvableScanwordDTO solvableScanwordDTO) {
        solvableScanwordRepository.save(toEntity(solvableScanwordDTO));
        return solvableScanwordDTO;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('save')")
    public SolvableScanwordDTO update(SolvableScanwordDTO solvableScanwordDTO) {
        if(!(solvableScanwordRepository.findById(solvableScanwordDTO.getId()).isPresent())) {
            throw new ResourceNotFoundException("SolvableScanword with id = " + solvableScanwordDTO.getId() + " not found", "");
        }
        return create(solvableScanwordDTO);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public boolean deleteById(Long id) {
        if (!(solvableScanwordRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("SolvableScanword with id = " + id + " not found", "");
        }
        solvableScanwordRepository.deleteById(id);
        return (solvableScanwordRepository.findById(id).isPresent());
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('read')")
    public SolvableScanwordDTO createByScanwordId(Long scanwordId) {
        UserDetails securityUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByName(securityUser.getUsername()).get();
        Scanword scaword = scanwordRepository.findById(scanwordId).get();
        SolvableScanwordDTO solvableScanwordDTO = new SolvableScanwordDTO();
        solvableScanwordDTO.setScanword(scaword);
        solvableScanwordDTO.setOwner(user);
        solvableScanwordDTO.setSolved(false);
        solvableScanwordDTO.setPrompt(scaword.getPrompt());
        solvableScanwordDTO.setSolvedQuestions(new ArrayList<>());
        solvableScanwordRepository.save(toEntity(solvableScanwordDTO));
        return solvableScanwordDTO;
    }

    @Override
    @PreAuthorize("hasAuthority('read')")
    public List<QuestionDTO> getAllByScanwordId(Long scanwordId) {
        UserDetails securityUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByName(securityUser.getUsername()).get();

        if (!solvableScanwordRepository.findByScanwordIdAndOwner(scanwordId, user).isPresent()) {
            createByScanwordId(scanwordId);
        }
        return QuestionMapper.QUESTION_MAPPER.allToDTO(solvableScanwordRepository.findByScanwordIdAndOwner(scanwordId, user).get().getSolvedQuestions());
    }

    @Override
    @PreAuthorize("hasAuthority('save')")
    public List<Question> updateResoledQuestionList(Long scanwordId, List<Question> questionList) {
        UserDetails securityUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByName(securityUser.getUsername()).get();
        SolvableScanwordDTO solvableScanwordDTO = toDTO(solvableScanwordRepository.findByScanwordIdAndOwner(scanwordId, user).get());
        solvableScanwordDTO.setSolvedQuestions(questionList);
        solvableScanwordRepository.save(toEntity(solvableScanwordDTO));
        return questionList;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('read')")
    public SolvableScanwordDTO decreasePromptById(Long id) {
        UserDetails securityUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByName(securityUser.getUsername()).get();

        SolvableScanword solvableScanword =  solvableScanwordRepository.findByScanwordIdAndOwner(id, user).get();
        solvableScanword.setPrompt(solvableScanword.getPrompt() - 1);
        solvableScanwordRepository.save(solvableScanword);
        return toDTO(solvableScanword);
    }

    @Override
    @PreAuthorize("hasAuthority('read')")
    public SolvableScanwordDTO getByScanwordId(Long scanwordId) {
        UserDetails securityUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByName(securityUser.getUsername()).get();
        if (!solvableScanwordRepository.findByScanwordIdAndOwner(scanwordId, user).isPresent()) {
           return createByScanwordId(scanwordId);
        }
        return toDTO(solvableScanwordRepository.findByScanwordIdAndOwner(scanwordId, user).get());

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

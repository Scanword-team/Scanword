package ru.scanword.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scanword.domain.ScanwordQuestion;
import ru.scanword.dto.ScanwordDTO;
import ru.scanword.dto.ScanwordQuestionAllDTO;
import ru.scanword.dto.ScanwordQuestionDTO;
import ru.scanword.exceptions.ResourceNotFoundException;
import ru.scanword.mapper.ScanwordMapper;
import ru.scanword.mapper.ScanwordQuestionMapper;
import ru.scanword.repository.QuestionRepository;
import ru.scanword.repository.ScanwordQuestionRepository;
import ru.scanword.service.ScanwordQuestionService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScanwordQuestionServiceImpl implements ScanwordQuestionService {

    private final ScanwordQuestionRepository scanwordQuestionRepository;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public List<ScanwordQuestionDTO> getAll() {
        return allToDTO(scanwordQuestionRepository.findAll());
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public List<ScanwordQuestionDTO> getAllByScanword(ScanwordDTO scanwordDTO) {
        return allToDTO(scanwordQuestionRepository.findAllByScanword(ScanwordMapper.SCANWORD_MAPPER.toEntity(scanwordDTO)));
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public ScanwordQuestionDTO getById(Long scanword_id, Long question_id) {
        if (!(scanwordQuestionRepository.findByScanwordIdAndQuestionId(scanword_id, question_id).isPresent())) {
            throw new ResourceNotFoundException("ScanwordQuestion with  scanword id = " + scanword_id + " and question id = " + question_id + " not found", "");
        }
        return toDTO(scanwordQuestionRepository.findByScanwordIdAndQuestionId(scanword_id, question_id).get());
    }

    @PreAuthorize("hasAuthority('read')")
    public List<ScanwordQuestionDTO> getAllByScanwordId(Long scanword_id) {
        return allToDTO(scanwordQuestionRepository.findAllByScanwordId(scanword_id));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public ScanwordQuestionDTO create(ScanwordQuestionDTO scanwordQuestionDTO) {
        scanwordQuestionRepository.save(toEntity(scanwordQuestionDTO));
        return scanwordQuestionDTO;
    }

    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public boolean createWithAllDTO(List<ScanwordQuestionAllDTO> scanwordQuestionDTO) {
        if (!scanwordQuestionDTO.isEmpty()) {
            List<ScanwordQuestion> sc = scanwordQuestionRepository.findAllByScanword(scanwordQuestionDTO.get(0).getScanword());
            scanwordQuestionRepository.deleteAll(sc);
        }
        List<ScanwordQuestion> scanwordQuestionList = new ArrayList<>();
        scanwordQuestionDTO.forEach(scan -> {
            ScanwordQuestion scanwordQuestion = new ScanwordQuestion();
            scanwordQuestion.setScanword(scan.getScanword());
            scanwordQuestion.setDirection(scan.getDirection());
            scanwordQuestion.setNumber(scan.getNumber());
            scanwordQuestion.setX(scan.getX());
            scanwordQuestion.setY(scan.getY());
            scanwordQuestion.setQuestion(questionRepository.getById(scan.getQuestionId()));
            scanwordQuestionList.add(scanwordQuestion);
        });
        scanwordQuestionRepository.saveAll(scanwordQuestionList);
        return true;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public ScanwordQuestionDTO update(ScanwordQuestionDTO scanwordQuestionDTO) {
        if (!(scanwordQuestionRepository.findByScanwordIdAndQuestionId(scanwordQuestionDTO.getScanword().getId(), scanwordQuestionDTO.getQuestion().getId()).isPresent())) {
            throw new ResourceNotFoundException("ScanwordQuestion with  scanword id = " + scanwordQuestionDTO.getScanword().getId() + " and question id = " + scanwordQuestionDTO.getQuestion().getId() + " not found", "");
        }
        return create(scanwordQuestionDTO);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public boolean deleteById(Long scanword_id, Long question_id) {
        if (!(scanwordQuestionRepository.findByScanwordIdAndQuestionId(scanword_id, question_id).isPresent())) {
            throw new ResourceNotFoundException("ScanwordQuestion with  scanword id = " + scanword_id + " and question id = " + question_id + " not found", "");
        }
        scanwordQuestionRepository.deleteByScanwordIdAndQuestionId(scanword_id, question_id);
        return (scanwordQuestionRepository.findByScanwordIdAndQuestionId(scanword_id, question_id).isPresent());
    }


    private ScanwordQuestion toEntity(ScanwordQuestionDTO scanwordQuestionDTO) {
        return ScanwordQuestionMapper.SCANWORD_QUESTION_MAPPER.toEntity(scanwordQuestionDTO);
    }

    private ScanwordQuestionDTO toDTO(ScanwordQuestion scanwordQuestion) {
        return ScanwordQuestionMapper.SCANWORD_QUESTION_MAPPER.toDTO(scanwordQuestion);
    }

    private List<ScanwordQuestion> allToEntity(List<ScanwordQuestionDTO> scanwordQuestionDTOList) {
        return ScanwordQuestionMapper.SCANWORD_QUESTION_MAPPER.allToEntity(scanwordQuestionDTOList);
    }

    private List<ScanwordQuestionDTO> allToDTO(List<ScanwordQuestion> scanwordQuestionList) {
        return ScanwordQuestionMapper.SCANWORD_QUESTION_MAPPER.allToDTO(scanwordQuestionList);
    }
}

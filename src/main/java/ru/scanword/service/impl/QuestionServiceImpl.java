package ru.scanword.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scanword.domain.Question;
import ru.scanword.dto.QuestionDTO;
import ru.scanword.exceptions.ResourceNotFoundException;
import ru.scanword.mapper.QuestionMapper;
import ru.scanword.repository.QuestionRepository;
import ru.scanword.service.QuestionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    //private final QuestionServiceImpl questionService;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public List<QuestionDTO> getAll() {
        return allToDTO(questionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public QuestionDTO getById(Long id) {
        if (!(questionRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("Question with id = " + id + " not found", "");
        }
        return toDTO(questionRepository.getById(id));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public QuestionDTO create(QuestionDTO questionDTO) {
        questionRepository.save(toEntity(questionDTO));
        return questionDTO;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public QuestionDTO update(QuestionDTO questionDTO) {
        if(!(questionRepository.findById(questionDTO.getId()).isPresent())) {
            throw new ResourceNotFoundException("Question with id = " + questionDTO.getId() + " not found", "");
        }
        return create(questionDTO);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public boolean deleteById(Long id) {
        if (!(questionRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("Question with id = " + id + " not found", "");
        }
        questionRepository.deleteById(id);
        return (questionRepository.findById(id).isPresent());
    }

    private Question toEntity(QuestionDTO questionDTO){
        return QuestionMapper.QUESTION_MAPPER.toEntity(questionDTO);
    }

    private QuestionDTO toDTO(Question question){
        return QuestionMapper.QUESTION_MAPPER.toDTO(question);
    }

    private List<Question> allToEntity(List<QuestionDTO> questionDTOList){
        return QuestionMapper.QUESTION_MAPPER.allToEntity(questionDTOList);
    }

    private List<QuestionDTO> allToDTO(List<Question> questionList){
        return QuestionMapper.QUESTION_MAPPER.allToDTO(questionList);
    }
}

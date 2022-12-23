package ru.scanword.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scanword.domain.Dictionary;
import ru.scanword.domain.Question;
import ru.scanword.dto.QuestinOnlyIdDTO;
import ru.scanword.dto.QuestionDTO;
import ru.scanword.exceptions.ResourceNotFoundException;
import ru.scanword.mapper.QuestionMapper;
import ru.scanword.repository.AudioRepository;
import ru.scanword.repository.DictionaryRepository;
import ru.scanword.repository.ImageRepository;
import ru.scanword.repository.QuestionRepository;
import ru.scanword.service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final DictionaryRepository dictionaryRepository;
    private final AudioRepository audioRepository;
    private final ImageRepository imageRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public List<QuestionDTO> getAll() {
        return allToDTO(questionRepository.findAll());
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public List<QuestionDTO> getAllByDictionaryId(Long id) {
        Optional<Dictionary> dictionary = dictionaryRepository.findById(id);
        if (dictionary.isPresent()) {
            return allToDTO(questionRepository.findQuestionByDictionary(dictionary.get()));
        } else {
            throw new ResourceNotFoundException("Dictionary with id = " + id + " not found", "");
        }
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
    @PreAuthorize("hasAuthority('create')")
    public QuestionDTO create(QuestionDTO questionDTO) {
        if (questionDTO.getAudio() != null) {
            questionDTO.setType("audio");
        } else if (questionDTO.getImage() != null) {
            questionDTO.setType("image");
        } else {
            questionDTO.setType("text");
        }
        questionRepository.save(toEntity(questionDTO));
        return questionDTO;
    }

    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public boolean saveAll(List<QuestinOnlyIdDTO> dtoList) {
        questionRepository.saveAll(convertOnlyIdDTOToEntity(dtoList));
        return true;
    }


    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public boolean deleteAll(List<QuestinOnlyIdDTO> dtoList) {
        questionRepository.deleteAll(convertOnlyIdDTOToEntity(dtoList));
        return true;
    }

    private List<Question> convertOnlyIdDTOToEntity(List<QuestinOnlyIdDTO> dtoList) {
        List<Question> questionList = new ArrayList<>();
        for (QuestinOnlyIdDTO questinOnlyIdDTO : dtoList) {
            Question question = new Question();
            question.setId(questinOnlyIdDTO.getId());
            question.setQuestion(questinOnlyIdDTO.getQuestion());
            question.setAnswer(questinOnlyIdDTO.getAnswer());
            question.setType(questinOnlyIdDTO.getType());
            question.setAudio(questinOnlyIdDTO.getAudioId() == null ? null : audioRepository.getById(questinOnlyIdDTO.getAudioId()));
            question.setImage(questinOnlyIdDTO.getImageId() == null ? null : imageRepository.getById(questinOnlyIdDTO.getImageId()));
            question.setDictionary(questinOnlyIdDTO.getDictionaryId() == null ? null : dictionaryRepository.getById(questinOnlyIdDTO.getDictionaryId()));
            questionList.add(question);
        }
        return questionList;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public QuestionDTO update(QuestionDTO questionDTO) {
        if (!(questionRepository.findById(questionDTO.getId()).isPresent())) {
            throw new ResourceNotFoundException("Question with id = " + questionDTO.getId() + " not found", "");
        }
        return create(questionDTO);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public boolean deleteById(Long id) {
        if (!(questionRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("Question with id = " + id + " not found", "");
        }
        questionRepository.deleteById(id);
        return (questionRepository.findById(id).isPresent());
    }

    private Question toEntity(QuestionDTO questionDTO) {
        return QuestionMapper.QUESTION_MAPPER.toEntity(questionDTO);
    }

    private QuestionDTO toDTO(Question question) {
        return QuestionMapper.QUESTION_MAPPER.toDTO(question);
    }

    private List<Question> allToEntity(List<QuestionDTO> questionDTOList) {
        return QuestionMapper.QUESTION_MAPPER.allToEntity(questionDTOList);
    }

    private List<QuestionDTO> allToDTO(List<Question> questionList) {
        return QuestionMapper.QUESTION_MAPPER.allToDTO(questionList);
    }
}

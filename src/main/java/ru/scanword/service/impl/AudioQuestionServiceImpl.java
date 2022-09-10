package ru.scanword.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scanword.domain.AudioQuestion;
import ru.scanword.dto.AudioQuestionDTO;
import ru.scanword.exceptions.ResourceNotFoundException;
import ru.scanword.mapper.AudioQuestionMapper;
import ru.scanword.repository.AudioQuestionRepository;
import ru.scanword.service.AudioQuestionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AudioQuestionServiceImpl implements AudioQuestionService {

    private final AudioQuestionRepository audioQuestionRepository;


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public List<AudioQuestionDTO> getAll() {
        return allToDTO(audioQuestionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public AudioQuestionDTO getById(Long id) {
        if (!(audioQuestionRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("AudioQuestion with id = " + id + " not found", "");
        }
        return toDTO(audioQuestionRepository.getById(id));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public AudioQuestionDTO create(AudioQuestionDTO audioQuestionDTO) {
        audioQuestionRepository.save(toEntity(audioQuestionDTO));
        return audioQuestionDTO;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public AudioQuestionDTO update(AudioQuestionDTO audioQuestionDTO) {
        if(!(audioQuestionRepository.findById(audioQuestionDTO.getId()).isPresent())) {
            throw new ResourceNotFoundException("AudioQuestion with id = " + audioQuestionDTO.getId() + " not found", "");
        }
        return create(audioQuestionDTO);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public boolean deleteById(Long id) {
        if (!(audioQuestionRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("AudioQuestion with id = " + id + " not found", "");
        }
        audioQuestionRepository.deleteById(id);
        return (audioQuestionRepository.findById(id).isPresent());
    }

    private AudioQuestion toEntity(AudioQuestionDTO audioQuestionDTO){
        return AudioQuestionMapper.AUDIO_QUESTION_MAPPER.toEntity(audioQuestionDTO);
    }

    private AudioQuestionDTO toDTO(AudioQuestion audioQuestion){
        return AudioQuestionMapper.AUDIO_QUESTION_MAPPER.toDTO(audioQuestion);
    }

    private List<AudioQuestion> allToEntity(List<AudioQuestionDTO> audioQuestionDTOList){
        return AudioQuestionMapper.AUDIO_QUESTION_MAPPER.allToEntity(audioQuestionDTOList);
    }

    private List<AudioQuestionDTO> allToDTO(List<AudioQuestion> audioQuestionList){
        return AudioQuestionMapper.AUDIO_QUESTION_MAPPER.allToDTO(audioQuestionList);
    }
}

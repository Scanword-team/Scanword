package ru.scanword.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scanword.domain.PictureQuestion;
import ru.scanword.dto.PictureQuestionDTO;
import ru.scanword.exceptions.ResourceNotFoundException;
import ru.scanword.mapper.PictureQuestionMapper;
import ru.scanword.repository.PictureQuestionRepository;
import ru.scanword.service.PictureQuestionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PictureQuestionServiceImpl implements PictureQuestionService {

    private final PictureQuestionRepository pictureQuestionRepository;

    //private final PictureQuestionServiceImpl pictureQuestionService;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public List<PictureQuestionDTO> getAll() {
        return allToDTO(pictureQuestionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('read')")
    public PictureQuestionDTO getById(Long id) {
        if (!(pictureQuestionRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("PictureQuestion with id = " + id + " not found", "");
        }
        return toDTO(pictureQuestionRepository.getById(id));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public PictureQuestionDTO create(PictureQuestionDTO pictureQuestionDTO) {
        pictureQuestionRepository.save(toEntity(pictureQuestionDTO));
        return pictureQuestionDTO;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public PictureQuestionDTO update(PictureQuestionDTO pictureQuestionDTO) {
        if(!(pictureQuestionRepository.findById(pictureQuestionDTO.getId()).isPresent())) {
            throw new ResourceNotFoundException("PictureQuestion with id = " + pictureQuestionDTO.getId() + " not found", "");
        }
        return create(pictureQuestionDTO);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('write')")
    public boolean deleteById(Long id) {
        if (!(pictureQuestionRepository.findById(id).isPresent())) {
            throw new ResourceNotFoundException("PictureQuestion with id = " + id + " not found", "");
        }
        pictureQuestionRepository.deleteById(id);
        return (pictureQuestionRepository.findById(id).isPresent());
    }

    private PictureQuestion toEntity(PictureQuestionDTO pictureQuestionDTO){
        return PictureQuestionMapper.PICTURE_QUESTION_MAPPER.toEntity(pictureQuestionDTO);
    }

    private PictureQuestionDTO toDTO(PictureQuestion pictureQuestion){
        return PictureQuestionMapper.PICTURE_QUESTION_MAPPER.toDTO(pictureQuestion);
    }

    private List<PictureQuestion> allToEntity(List<PictureQuestionDTO> pictureQuestionDTOList){
        return PictureQuestionMapper.PICTURE_QUESTION_MAPPER.allToEntity(pictureQuestionDTOList);
    }

    private List<PictureQuestionDTO> allToDTO(List<PictureQuestion> pictureQuestionList){
        return PictureQuestionMapper.PICTURE_QUESTION_MAPPER.allToDTO(pictureQuestionList);
    }
}

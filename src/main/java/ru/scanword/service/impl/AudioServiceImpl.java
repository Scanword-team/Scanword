package ru.scanword.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.scanword.domain.Audio;
import ru.scanword.dto.AudioDTO;
import ru.scanword.exceptions.ResourceNotFoundException;
import ru.scanword.mapper.AudioMapper;
import ru.scanword.repository.AudioRepository;
import ru.scanword.repository.QuestionRepository;
import ru.scanword.service.AudioService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AudioServiceImpl implements AudioService {

    private final AudioRepository audioRepository;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('read')")
    public List<AudioDTO> getAll() {
        return allToDTO(audioRepository.findAll());
    }


    @Transactional
    @PreAuthorize("hasAuthority('read')")
    public List<AudioDTO> getAllUsed() {
        List<Audio> audioList = audioRepository.findAll();
        audioList.removeIf(audio ->
           !questionRepository.findAllByAudio(audio).isEmpty()
        );
        return allToDTO(audioList);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('read')")
    public AudioDTO getById(Long id) {
        Optional<Audio> audio = audioRepository.findById(id);
        if (audio.isPresent()) {
            return toDTO(audio.get());
        } else {
            throw new ResourceNotFoundException("Audio with id = " + id + " not found", "");
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public AudioDTO create(AudioDTO audioDTO) {
        audioRepository.save(toEntity(audioDTO));
        return audioDTO;
    }

    @Transactional
    public boolean saveAll(List<AudioDTO> audioDTOS) {
        List<Audio> audios = audioRepository.findAll();
        audioDTOS.forEach(audio-> {
            audios.removeIf(a-> a.getId().equals(audio.getId()));
        });

        audioRepository.saveAll(allToEntity(audioDTOS));
        audios.forEach(audio -> {
            if(questionRepository.findQuestionByAudio(audio).isEmpty()) {
                audioRepository.deleteById(audio.getId());
            }
        });
        return true;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public AudioDTO update(AudioDTO audioDTO) {
        audioRepository.save(toEntity(audioDTO));
        return audioDTO;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('create')")
    public boolean deleteById(Long id) {
        Optional<Audio> audio = audioRepository.findById(id);
        if (!audio.isPresent()) {
            return false;
        }
        audioRepository.delete(audio.get());
        return true;
    }

    private Audio toEntity(AudioDTO audioDTO) {
        return AudioMapper.AUDIO_MAPPER.toEntity(audioDTO);
    }

    private AudioDTO toDTO(Audio audio) {
        return AudioMapper.AUDIO_MAPPER.toDTO(audio);
    }

    private List<Audio> allToEntity(List<AudioDTO> audioDTOS) {
        return AudioMapper.AUDIO_MAPPER.allToEntity(audioDTOS);
    }

    private List<AudioDTO> allToDTO(List<Audio> audioList) {
        return AudioMapper.AUDIO_MAPPER.allToDTO(audioList);
    }


}

package ru.scanword.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.scanword.domain.Audio;
import ru.scanword.domain.Dictionary;
import ru.scanword.dto.AudioDTO;
import ru.scanword.dto.DictionaryDTO;

import java.util.List;

@Mapper
public interface AudioMapper {
    AudioMapper AUDIO_MAPPER = Mappers.getMapper(AudioMapper.class);

    Audio toEntity(AudioDTO questionDTO);

    AudioDTO toDTO(Audio question);

    List<Audio> allToEntity(List<AudioDTO> questionDTOList);

    List<AudioDTO> allToDTO(List<Audio> questionList);
}

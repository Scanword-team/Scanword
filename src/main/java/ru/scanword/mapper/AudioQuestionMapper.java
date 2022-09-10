package ru.scanword.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.scanword.domain.AudioQuestion;
import ru.scanword.dto.AudioQuestionDTO;

import java.util.List;

@Mapper
public interface AudioQuestionMapper {

    AudioQuestionMapper AUDIO_QUESTION_MAPPER = Mappers.getMapper(AudioQuestionMapper.class);

    AudioQuestion toEntity(AudioQuestionDTO audioQuestionDTO);

    @Mapping(target = "id", ignore = true)
    AudioQuestionDTO toDTO(AudioQuestion audioQuestion);
    List<AudioQuestion> allToEntity(List<AudioQuestionDTO> audioQuestionDTOList);
    List<AudioQuestionDTO> allToDTO(List<AudioQuestion> audioQuestionList);

}

package ru.scanword.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.scanword.domain.PictureQuestion;
import ru.scanword.dto.PictureQuestionDTO;

import java.util.List;

@Mapper
public interface PictureQuestionMapper {

    PictureQuestionMapper PICTURE_QUESTION_MAPPER = Mappers.getMapper(PictureQuestionMapper.class);

    PictureQuestion toEntity(PictureQuestionDTO pictureQuestionDTO);

    @Mapping(target = "id", ignore = true)
    PictureQuestionDTO toDTO(PictureQuestion pictureQuestion);
    List<PictureQuestion> allToEntity(List<PictureQuestionDTO> pictureQuestionDTOList);
    List<PictureQuestionDTO> allToDTO(List<PictureQuestion> pictureQuestionList);

}

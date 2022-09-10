package ru.scanword.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.scanword.domain.Question;
import ru.scanword.dto.QuestionDTO;

import java.util.List;

@Mapper
public interface QuestionMapper {

    QuestionMapper QUESTION_MAPPER = Mappers.getMapper(QuestionMapper.class);

    Question toEntity(QuestionDTO questionDTO);

    @Mapping(target = "id", ignore = true)
    QuestionDTO toDTO(Question question);
    List<Question> allToEntity(List<QuestionDTO> questionDTOList);
    List<QuestionDTO> allToDTO(List<Question> questionList);

}

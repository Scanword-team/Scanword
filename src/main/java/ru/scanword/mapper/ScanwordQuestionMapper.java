package ru.scanword.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.scanword.domain.ScanwordQuestion;
import ru.scanword.dto.ScanwordQuestionDTO;

import java.util.List;

@Mapper
public interface ScanwordQuestionMapper {

    ScanwordQuestionMapper SCANWORD_QUESTION_MAPPER = Mappers.getMapper(ScanwordQuestionMapper.class);

    ScanwordQuestion toEntity(ScanwordQuestionDTO scanwordQuestionDTO);

    //@Mapping(target = "id", ignore = true)
    ScanwordQuestionDTO toDTO(ScanwordQuestion scanwordQuestion);
    List<ScanwordQuestion> allToEntity(List<ScanwordQuestionDTO> scanwordQuestionDTOList);
    List<ScanwordQuestionDTO> allToDTO(List<ScanwordQuestion> scanwordQuestionList);

}

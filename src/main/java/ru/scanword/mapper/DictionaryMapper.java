package ru.scanword.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.scanword.domain.Dictionary;
import ru.scanword.domain.Question;
import ru.scanword.dto.DictionaryDTO;
import ru.scanword.dto.QuestionDTO;

import java.util.List;

@Mapper
public interface DictionaryMapper {

    DictionaryMapper DICTIONARY_MAPPER = Mappers.getMapper(DictionaryMapper.class);

    Dictionary toEntity(DictionaryDTO questionDTO);

    DictionaryDTO toDTO(Dictionary question);

    List<Dictionary> allToEntity(List<DictionaryDTO> questionDTOList);

    List<DictionaryDTO> allToDTO(List<Dictionary> questionList);
}

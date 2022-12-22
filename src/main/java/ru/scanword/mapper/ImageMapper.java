package ru.scanword.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.scanword.domain.Audio;
import ru.scanword.domain.Image;
import ru.scanword.dto.AudioDTO;
import ru.scanword.dto.ImageDTO;

import java.util.List;

@Mapper
public interface ImageMapper {
    ImageMapper IMAGE_MAPPER = Mappers.getMapper(ImageMapper.class);

    Image toEntity(ImageDTO questionDTO);

    ImageDTO toDTO(Image question);

    List<Image> allToEntity(List<ImageDTO> questionDTOList);

    List<ImageDTO> allToDTO(List<Image> questionList);
}

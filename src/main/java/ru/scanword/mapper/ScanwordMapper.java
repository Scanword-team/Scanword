package ru.scanword.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.scanword.domain.Scanword;
import ru.scanword.dto.ScanwordDTO;

import java.util.List;

@Mapper
public interface ScanwordMapper {

    ScanwordMapper SCANWORD_MAPPER = Mappers.getMapper(ScanwordMapper.class);

    Scanword toEntity(ScanwordDTO scanwordDTO);

    @Mapping(target = "id", ignore = true)
    ScanwordDTO toDTO(Scanword scanword);
    List<Scanword> allToEntity(List<ScanwordDTO> scanwordDTOList);
    List<ScanwordDTO> allToDTO(List<Scanword> scanwordList);

}

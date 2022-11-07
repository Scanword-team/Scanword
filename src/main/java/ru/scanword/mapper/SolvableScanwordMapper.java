package ru.scanword.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.scanword.domain.SolvableScanword;
import ru.scanword.dto.SolvableScanwordDTO;

import java.util.List;

@Mapper
public interface SolvableScanwordMapper {

    SolvableScanwordMapper SOLVABLE_SCANWORD_MAPPER = Mappers.getMapper(SolvableScanwordMapper.class);

    SolvableScanword toEntity(SolvableScanwordDTO solvableScanwordDTO);

    //@Mapping(target = "id", ignore = true)
    SolvableScanwordDTO toDTO(SolvableScanword solvableScanword);
    List<SolvableScanword> allToEntity(List<SolvableScanwordDTO> solvableScanwordDTOList);
    List<SolvableScanwordDTO> allToDTO(List<SolvableScanword> solvableScanwordList);

}

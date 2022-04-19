package uz.digitalone.houzingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uz.digitalone.houzingapp.dto.request.HouseComponentsDto;
import uz.digitalone.houzingapp.dto.response.HouseComponentsResponseDto;
import uz.digitalone.houzingapp.entity.HouseComponents;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HouseComponentMapper {

    HouseComponentMapper INSTANCE = Mappers.getMapper(HouseComponentMapper.class);

    HouseComponentsDto fromEntity(HouseComponents houseComponents);

    List<HouseComponentsDto> fromEntities(List<HouseComponents> houseComponents);

    @Mapping(target = "id", ignore = true)
    HouseComponents fromDto(HouseComponentsDto houseComponentsDto);

    HouseComponentsResponseDto toDto(HouseComponents houseComponents);

}

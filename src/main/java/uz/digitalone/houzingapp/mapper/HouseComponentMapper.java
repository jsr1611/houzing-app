package uz.digitalone.houzingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uz.digitalone.houzingapp.dto.response.HouseComponentsDto;
import uz.digitalone.houzingapp.entity.HouseComponents;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HouseComponentMapper {

    HouseComponentMapper INSTANCE = Mappers.getMapper(HouseComponentMapper.class);

    uz.digitalone.houzingapp.dto.request.HouseComponentsDto fromEntity(HouseComponents houseComponents);

    List<uz.digitalone.houzingapp.dto.request.HouseComponentsDto> fromEntities(List<HouseComponents> houseComponents);

    @Mapping(target = "id", ignore = true)
    HouseComponents fromDto(uz.digitalone.houzingapp.dto.request.HouseComponentsDto houseComponentsDto);

    HouseComponentsDto toDto(HouseComponents houseComponents);

}

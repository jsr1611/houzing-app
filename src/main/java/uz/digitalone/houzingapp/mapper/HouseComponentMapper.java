package uz.digitalone.houzingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uz.digitalone.houzingapp.dto.request.HouseComponentsDto;
import uz.digitalone.houzingapp.entity.HouseComponents;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HouseComponentMapper {

    HouseComponentMapper INSTANCE = Mappers.getMapper(HouseComponentMapper.class);

    @Mapping(target = "id", ignore = true)
    HouseComponentsDto fromEntity(HouseComponents houseComponents);

    List<HouseComponentsDto> fromEntities(List<HouseComponents> houseComponents);

    HouseComponents fromDto(HouseComponentsDto houseComponentsDto);



}

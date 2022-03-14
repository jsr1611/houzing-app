package uz.digitalone.houzingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.digitalone.houzingapp.dto.response.HouseDto;
import uz.digitalone.houzingapp.entity.House;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HouseMapper {
    HouseMapper INSTANCE = Mappers.getMapper(HouseMapper.class);

    // Entity to DTO
    HouseDto fromEntity(House house);

    //Entities to DTOs
    List<HouseDto> fromEntities(List<House> houses);

    // DTO to Entity
    House fromDTO(uz.digitalone.houzingapp.dto.request.HouseDto dto);

    // DTOs to Entities
    List<House> fromDTOs(List<uz.digitalone.houzingapp.dto.request.HouseDto> dtos);
}

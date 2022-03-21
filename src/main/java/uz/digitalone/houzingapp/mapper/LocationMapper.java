package uz.digitalone.houzingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.digitalone.houzingapp.dto.request.LocationDto;
import uz.digitalone.houzingapp.entity.Location;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);


    //From Entity to DTO
    LocationDto fromEntity(Location location);

    //From Entities to DTOs
    List<LocationDto> fromEntities(List<Location> locations);


    //From DTO to Entity
    Location fromDTO(LocationDto dto);

    //From DTOs to Entities
    List<Location> fromDTOs(List<LocationDto> dtos);


}

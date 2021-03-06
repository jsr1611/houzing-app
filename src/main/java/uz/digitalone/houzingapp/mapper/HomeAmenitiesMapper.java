package uz.digitalone.houzingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uz.digitalone.houzingapp.dto.request.HomeAmenitiesDto;
import uz.digitalone.houzingapp.dto.response.HomeAmenitiesResponseDto;
import uz.digitalone.houzingapp.entity.HomeAmenities;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HomeAmenitiesMapper {

    HomeAmenitiesMapper INSTANCE = Mappers.getMapper(HomeAmenitiesMapper.class);

    HomeAmenitiesDto fromEntity(HomeAmenities homeAmenities);

    List<HomeAmenitiesDto> fromEntities(List<HomeAmenities> homeAmenities);

    @Mapping(target = "id", ignore = true)
    HomeAmenities fromDto(HomeAmenitiesDto homeAmenitiesDto);

    HomeAmenitiesResponseDto toDto(HomeAmenities homeAmenities);

}

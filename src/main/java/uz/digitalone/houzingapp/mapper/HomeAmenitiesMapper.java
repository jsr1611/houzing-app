package uz.digitalone.houzingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uz.digitalone.houzingapp.dto.response.HomeAmenitiesDto;
import uz.digitalone.houzingapp.entity.HomeAmenities;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HomeAmenitiesMapper {

    HomeAmenitiesMapper INSTANCE = Mappers.getMapper(HomeAmenitiesMapper.class);

    uz.digitalone.houzingapp.dto.request.HomeAmenitiesDto fromEntity(HomeAmenities homeAmenities);

    List<uz.digitalone.houzingapp.dto.request.HomeAmenitiesDto> fromEntities(List<HomeAmenities> homeAmenities);

    @Mapping(target = "id", ignore = true)
    HomeAmenities fromDto(uz.digitalone.houzingapp.dto.request.HomeAmenitiesDto homeAmenitiesDto);

    HomeAmenitiesDto toDto(HomeAmenities homeAmenities);

}

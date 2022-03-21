package uz.digitalone.houzingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.digitalone.houzingapp.dto.request.HouseDetailsDto;
import uz.digitalone.houzingapp.entity.HouseDetails;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HouseDetailsMapper {
    HouseDetailsMapper INSTANCE = Mappers.getMapper(HouseDetailsMapper.class);

    //From Entity to DTO
    HouseDetailsDto fromEntity(HouseDetails houseDetails);

    //From Entities to DTOs
    List<HouseDetailsDto> fromEntities(List<HouseDetails> houseDetails);


    //From DTO to Entity
    HouseDetails fromDTO(HouseDetailsDto detailsDto);

    //From DTOs to Entities
    List<HouseDetails> fromDTOs(List<HouseDetailsDto> dtos);

}

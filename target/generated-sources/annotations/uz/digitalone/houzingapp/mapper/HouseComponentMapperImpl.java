package uz.digitalone.houzingapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import uz.digitalone.houzingapp.dto.request.HouseComponentsDto;
import uz.digitalone.houzingapp.dto.request.HouseComponentsDto.HouseComponentsDtoBuilder;
import uz.digitalone.houzingapp.dto.response.HouseComponentsResponseDto;
import uz.digitalone.houzingapp.dto.response.HouseComponentsResponseDto.HouseComponentsResponseDtoBuilder;
import uz.digitalone.houzingapp.entity.HouseComponents;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-20T00:00:22+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_302 (Amazon.com Inc.)"
)
@Component
public class HouseComponentMapperImpl implements HouseComponentMapper {

    @Override
    public HouseComponentsDto fromEntity(HouseComponents houseComponents) {
        if ( houseComponents == null ) {
            return null;
        }

        HouseComponentsDtoBuilder<?, ?> houseComponentsDto = HouseComponentsDto.builder();

        houseComponentsDto.tv( houseComponents.isTv() );
        houseComponentsDto.furniture( houseComponents.isFurniture() );
        houseComponentsDto.airCondition( houseComponents.isAirCondition() );
        houseComponentsDto.gasStove( houseComponents.isGasStove() );
        houseComponentsDto.internet( houseComponents.isInternet() );
        houseComponentsDto.courtyard( houseComponents.isCourtyard() );
        houseComponentsDto.additional( houseComponents.getAdditional() );

        return houseComponentsDto.build();
    }

    @Override
    public List<HouseComponentsDto> fromEntities(List<HouseComponents> houseComponents) {
        if ( houseComponents == null ) {
            return null;
        }

        List<HouseComponentsDto> list = new ArrayList<HouseComponentsDto>( houseComponents.size() );
        for ( HouseComponents houseComponents1 : houseComponents ) {
            list.add( fromEntity( houseComponents1 ) );
        }

        return list;
    }

    @Override
    public HouseComponents fromDto(HouseComponentsDto houseComponentsDto) {
        if ( houseComponentsDto == null ) {
            return null;
        }

        HouseComponents houseComponents = new HouseComponents();

        houseComponents.setTv( houseComponentsDto.isTv() );
        houseComponents.setFurniture( houseComponentsDto.isFurniture() );
        houseComponents.setAirCondition( houseComponentsDto.isAirCondition() );
        houseComponents.setGasStove( houseComponentsDto.isGasStove() );
        houseComponents.setInternet( houseComponentsDto.isInternet() );
        houseComponents.setCourtyard( houseComponentsDto.isCourtyard() );
        houseComponents.setAdditional( houseComponentsDto.getAdditional() );

        return houseComponents;
    }

    @Override
    public HouseComponentsResponseDto toDto(HouseComponents houseComponents) {
        if ( houseComponents == null ) {
            return null;
        }

        HouseComponentsResponseDtoBuilder<?, ?> houseComponentsResponseDto = HouseComponentsResponseDto.builder();

        houseComponentsResponseDto.id( houseComponents.getId() );
        houseComponentsResponseDto.tv( houseComponents.isTv() );
        houseComponentsResponseDto.furniture( houseComponents.isFurniture() );
        houseComponentsResponseDto.airCondition( houseComponents.isAirCondition() );
        houseComponentsResponseDto.gasStove( houseComponents.isGasStove() );
        houseComponentsResponseDto.internet( houseComponents.isInternet() );
        houseComponentsResponseDto.courtyard( houseComponents.isCourtyard() );
        houseComponentsResponseDto.additional( houseComponents.getAdditional() );

        return houseComponentsResponseDto.build();
    }
}

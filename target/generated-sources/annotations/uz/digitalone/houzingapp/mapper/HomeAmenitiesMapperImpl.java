package uz.digitalone.houzingapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import uz.digitalone.houzingapp.dto.request.HomeAmenitiesDto;
import uz.digitalone.houzingapp.dto.request.HomeAmenitiesDto.HomeAmenitiesDtoBuilder;
import uz.digitalone.houzingapp.dto.response.HomeAmenitiesResponseDto;
import uz.digitalone.houzingapp.dto.response.HomeAmenitiesResponseDto.HomeAmenitiesResponseDtoBuilder;
import uz.digitalone.houzingapp.entity.HomeAmenities;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-17T17:12:19+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
@Component
public class HomeAmenitiesMapperImpl implements HomeAmenitiesMapper {

    @Override
    public HomeAmenitiesDto fromEntity(HomeAmenities homeAmenities) {
        if ( homeAmenities == null ) {
            return null;
        }

        HomeAmenitiesDtoBuilder<?, ?> homeAmenitiesDto = HomeAmenitiesDto.builder();

        homeAmenitiesDto.school( homeAmenities.isSchool() );
        homeAmenitiesDto.park( homeAmenities.isPark() );
        homeAmenitiesDto.parking( homeAmenities.isParking() );
        homeAmenitiesDto.superMarket( homeAmenities.isSuperMarket() );
        homeAmenitiesDto.market( homeAmenities.isMarket() );
        homeAmenitiesDto.stadium( homeAmenities.isStadium() );
        homeAmenitiesDto.subway( homeAmenities.isSubway() );
        homeAmenitiesDto.garden( homeAmenities.isGarden() );
        homeAmenitiesDto.busStop( homeAmenities.isBusStop() );
        homeAmenitiesDto.additional( homeAmenities.getAdditional() );

        return homeAmenitiesDto.build();
    }

    @Override
    public List<HomeAmenitiesDto> fromEntities(List<HomeAmenities> homeAmenities) {
        if ( homeAmenities == null ) {
            return null;
        }

        List<HomeAmenitiesDto> list = new ArrayList<HomeAmenitiesDto>( homeAmenities.size() );
        for ( HomeAmenities homeAmenities1 : homeAmenities ) {
            list.add( fromEntity( homeAmenities1 ) );
        }

        return list;
    }

    @Override
    public HomeAmenities fromDto(HomeAmenitiesDto homeAmenitiesDto) {
        if ( homeAmenitiesDto == null ) {
            return null;
        }

        HomeAmenities homeAmenities = new HomeAmenities();

        homeAmenities.setSchool( homeAmenitiesDto.isSchool() );
        homeAmenities.setPark( homeAmenitiesDto.isPark() );
        homeAmenities.setParking( homeAmenitiesDto.isParking() );
        homeAmenities.setSuperMarket( homeAmenitiesDto.isSuperMarket() );
        homeAmenities.setMarket( homeAmenitiesDto.isMarket() );
        homeAmenities.setStadium( homeAmenitiesDto.isStadium() );
        homeAmenities.setSubway( homeAmenitiesDto.isSubway() );
        homeAmenities.setGarden( homeAmenitiesDto.isGarden() );
        homeAmenities.setBusStop( homeAmenitiesDto.isBusStop() );
        homeAmenities.setAdditional( homeAmenitiesDto.getAdditional() );

        return homeAmenities;
    }

    @Override
    public HomeAmenitiesResponseDto toDto(HomeAmenities homeAmenities) {
        if ( homeAmenities == null ) {
            return null;
        }

        HomeAmenitiesResponseDtoBuilder<?, ?> homeAmenitiesResponseDto = HomeAmenitiesResponseDto.builder();

        homeAmenitiesResponseDto.id( homeAmenities.getId() );
        homeAmenitiesResponseDto.school( homeAmenities.isSchool() );
        homeAmenitiesResponseDto.park( homeAmenities.isPark() );
        homeAmenitiesResponseDto.parking( homeAmenities.isParking() );
        homeAmenitiesResponseDto.superMarket( homeAmenities.isSuperMarket() );
        homeAmenitiesResponseDto.market( homeAmenities.isMarket() );
        homeAmenitiesResponseDto.stadium( homeAmenities.isStadium() );
        homeAmenitiesResponseDto.subway( homeAmenities.isSubway() );
        homeAmenitiesResponseDto.garden( homeAmenities.isGarden() );
        homeAmenitiesResponseDto.busStop( homeAmenities.isBusStop() );
        homeAmenitiesResponseDto.additional( homeAmenities.getAdditional() );

        return homeAmenitiesResponseDto.build();
    }
}

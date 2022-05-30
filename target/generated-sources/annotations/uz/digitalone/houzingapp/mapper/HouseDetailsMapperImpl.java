package uz.digitalone.houzingapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import uz.digitalone.houzingapp.dto.request.HouseDetailsDto;
import uz.digitalone.houzingapp.entity.HouseDetails;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-26T21:21:00+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
@Component
public class HouseDetailsMapperImpl implements HouseDetailsMapper {

    @Override
    public HouseDetailsDto fromEntity(HouseDetails houseDetails) {
        if ( houseDetails == null ) {
            return null;
        }

        HouseDetailsDto houseDetailsDto = new HouseDetailsDto();

        houseDetailsDto.setRoom( houseDetails.getRoom() );
        houseDetailsDto.setBath( houseDetails.getBath() );
        houseDetailsDto.setGarage( houseDetails.getGarage() );
        houseDetailsDto.setArea( houseDetails.getArea() );
        houseDetailsDto.setBeds( houseDetails.getBeds() );
        houseDetailsDto.setYearBuilt( houseDetails.getYearBuilt() );

        return houseDetailsDto;
    }

    @Override
    public List<HouseDetailsDto> fromEntities(List<HouseDetails> houseDetails) {
        if ( houseDetails == null ) {
            return null;
        }

        List<HouseDetailsDto> list = new ArrayList<HouseDetailsDto>( houseDetails.size() );
        for ( HouseDetails houseDetails1 : houseDetails ) {
            list.add( fromEntity( houseDetails1 ) );
        }

        return list;
    }

    @Override
    public HouseDetails fromDTO(HouseDetailsDto detailsDto) {
        if ( detailsDto == null ) {
            return null;
        }

        HouseDetails houseDetails = new HouseDetails();

        houseDetails.setRoom( detailsDto.getRoom() );
        houseDetails.setBath( detailsDto.getBath() );
        houseDetails.setGarage( detailsDto.getGarage() );
        houseDetails.setArea( detailsDto.getArea() );
        houseDetails.setBeds( detailsDto.getBeds() );
        houseDetails.setYearBuilt( detailsDto.getYearBuilt() );

        return houseDetails;
    }

    @Override
    public List<HouseDetails> fromDTOs(List<HouseDetailsDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<HouseDetails> list = new ArrayList<HouseDetails>( dtos.size() );
        for ( HouseDetailsDto houseDetailsDto : dtos ) {
            list.add( fromDTO( houseDetailsDto ) );
        }

        return list;
    }
}
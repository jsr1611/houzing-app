package uz.digitalone.houzingapp.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import uz.digitalone.houzingapp.dto.request.LocationDto;
import uz.digitalone.houzingapp.entity.Location;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-12T11:21:06+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
*/
@Component
public class LocationMapperImpl implements LocationMapper {

    @Override
    public LocationDto fromEntity(Location location) {
        if ( location == null ) {
            return null;
        }

        LocationDto locationDto = new LocationDto();

        locationDto.setLongitude( location.getLongitude() );
        locationDto.setLatitude( location.getLatitude() );

        return locationDto;
    }

    @Override
    public List<LocationDto> fromEntities(List<Location> locations) {
        if ( locations == null ) {
            return null;
        }

        List<LocationDto> list = new ArrayList<LocationDto>( locations.size() );
        for ( Location location : locations ) {
            list.add( fromEntity( location ) );
        }

        return list;
    }

    @Override
    public Location fromDTO(LocationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Location location = new Location();

        location.setLongitude( dto.getLongitude() );
        location.setLatitude( dto.getLatitude() );

        return location;
    }

    @Override
    public List<Location> fromDTOs(List<LocationDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Location> list = new ArrayList<Location>( dtos.size() );
        for ( LocationDto locationDto : dtos ) {
            list.add( fromDTO( locationDto ) );
        }

        return list;
    }
}

package uz.digitalone.houzingapp.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import uz.digitalone.houzingapp.dto.request.AttachmentDto;
import uz.digitalone.houzingapp.dto.request.HouseDetailsDto;
import uz.digitalone.houzingapp.dto.request.LocationDto;
import uz.digitalone.houzingapp.dto.response.CategoryDto;
import uz.digitalone.houzingapp.dto.response.HouseDto;
import uz.digitalone.houzingapp.dto.response.HouseDto.HouseDtoBuilder;
import uz.digitalone.houzingapp.dto.response.UserDto;
import uz.digitalone.houzingapp.entity.Attachment;
import uz.digitalone.houzingapp.entity.Category;
import uz.digitalone.houzingapp.entity.House;
import uz.digitalone.houzingapp.entity.HouseDetails;
import uz.digitalone.houzingapp.entity.Location;
import uz.digitalone.houzingapp.entity.User;
import uz.digitalone.houzingapp.enums.Status;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-18T11:31:20+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
@Component
public class HouseMapperImpl implements HouseMapper {

    @Override
    public HouseDto fromEntity(House house) {
        if ( house == null ) {
            return null;
        }

        HouseDtoBuilder houseDto = HouseDto.builder();

        houseDto.id( house.getId() );
        houseDto.name( house.getName() );
        houseDto.description( house.getDescription() );
        houseDto.user( userToUserDto( house.getUser() ) );
        houseDto.houseDetails( houseDetailsToHouseDetailsDto( house.getHouseDetails() ) );
        houseDto.price( house.getPrice() );
        houseDto.salePrice( house.getSalePrice() );
        houseDto.location( locationToLocationDto( house.getLocation() ) );
        houseDto.address( house.getAddress() );
        houseDto.city( house.getCity() );
        houseDto.region( house.getRegion() );
        houseDto.country( house.getCountry() );
        houseDto.zipCode( house.getZipCode() );
        houseDto.attachments( attachmentSetToAttachmentDtoSet( house.getAttachments() ) );
        houseDto.category( categoryToCategoryDto( house.getCategory() ) );
        houseDto.isSold( house.getIsSold() );
        houseDto.favorite( house.getFavorite() );
        if ( house.getStatus() != null ) {
            houseDto.status( house.getStatus().name() );
        }

        return houseDto.build();
    }

    @Override
    public List<HouseDto> fromEntities(List<House> houses) {
        if ( houses == null ) {
            return null;
        }

        List<HouseDto> list = new ArrayList<HouseDto>( houses.size() );
        for ( House house : houses ) {
            list.add( fromEntity( house ) );
        }

        return list;
    }

    @Override
    public House fromDTO(uz.digitalone.houzingapp.dto.request.HouseDto dto) {
        if ( dto == null ) {
            return null;
        }

        House house = new House();

        house.setName( dto.getName() );
        house.setDescription( dto.getDescription() );
        house.setHouseDetails( houseDetailsDtoToHouseDetails( dto.getHouseDetails() ) );
        house.setPrice( dto.getPrice() );
        house.setSalePrice( dto.getSalePrice() );
        house.setAddress( dto.getAddress() );
        house.setCity( dto.getCity() );
        house.setRegion( dto.getRegion() );
        house.setCountry( dto.getCountry() );
        house.setZipCode( dto.getZipCode() );
        house.setAttachments( attachmentDtoSetToAttachmentSet( dto.getAttachments() ) );
        house.setIsSold( dto.getIsSold() );
        house.setFavorite( dto.getFavorite() );
        if ( dto.getStatus() != null ) {
            house.setStatus( Enum.valueOf( Status.class, dto.getStatus() ) );
        }

        return house;
    }

    @Override
    public List<House> fromDTOs(List<uz.digitalone.houzingapp.dto.request.HouseDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<House> list = new ArrayList<House>( dtos.size() );
        for ( uz.digitalone.houzingapp.dto.request.HouseDto houseDto : dtos ) {
            list.add( fromDTO( houseDto ) );
        }

        return list;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setFirstname( user.getFirstname() );
        userDto.setLastname( user.getLastname() );
        userDto.setEmail( user.getEmail() );

        return userDto;
    }

    protected HouseDetailsDto houseDetailsToHouseDetailsDto(HouseDetails houseDetails) {
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

    protected LocationDto locationToLocationDto(Location location) {
        if ( location == null ) {
            return null;
        }

        LocationDto locationDto = new LocationDto();

        locationDto.setLongitude( location.getLongitude() );
        locationDto.setLatitude( location.getLatitude() );

        return locationDto;
    }

    protected AttachmentDto attachmentToAttachmentDto(Attachment attachment) {
        if ( attachment == null ) {
            return null;
        }

        AttachmentDto attachmentDto = new AttachmentDto();

        attachmentDto.setImgPath( attachment.getImgPath() );

        return attachmentDto;
    }

    protected Set<AttachmentDto> attachmentSetToAttachmentDtoSet(Set<Attachment> set) {
        if ( set == null ) {
            return null;
        }

        Set<AttachmentDto> set1 = new HashSet<AttachmentDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Attachment attachment : set ) {
            set1.add( attachmentToAttachmentDto( attachment ) );
        }

        return set1;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setName( category.getName() );

        return categoryDto;
    }

    protected HouseDetails houseDetailsDtoToHouseDetails(HouseDetailsDto houseDetailsDto) {
        if ( houseDetailsDto == null ) {
            return null;
        }

        HouseDetails houseDetails = new HouseDetails();

        houseDetails.setRoom( houseDetailsDto.getRoom() );
        houseDetails.setBath( houseDetailsDto.getBath() );
        houseDetails.setGarage( houseDetailsDto.getGarage() );
        houseDetails.setArea( houseDetailsDto.getArea() );
        houseDetails.setBeds( houseDetailsDto.getBeds() );
        houseDetails.setYearBuilt( houseDetailsDto.getYearBuilt() );

        return houseDetails;
    }

    protected Attachment attachmentDtoToAttachment(AttachmentDto attachmentDto) {
        if ( attachmentDto == null ) {
            return null;
        }

        Attachment attachment = new Attachment();

        attachment.setImgPath( attachmentDto.getImgPath() );

        return attachment;
    }

    protected Set<Attachment> attachmentDtoSetToAttachmentSet(Set<AttachmentDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Attachment> set1 = new HashSet<Attachment>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AttachmentDto attachmentDto : set ) {
            set1.add( attachmentDtoToAttachment( attachmentDto ) );
        }

        return set1;
    }
}

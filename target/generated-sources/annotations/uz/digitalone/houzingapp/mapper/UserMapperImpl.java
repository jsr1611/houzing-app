package uz.digitalone.houzingapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import uz.digitalone.houzingapp.dto.request.RegUserDto;
import uz.digitalone.houzingapp.dto.response.UserDto;
import uz.digitalone.houzingapp.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-26T21:21:00+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto fromEntity(User user) {
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

    @Override
    public List<UserDto> fromEntities(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( fromEntity( user ) );
        }

        return list;
    }

    @Override
    public User fromDTO(RegUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setFirstname( dto.getFirstname() );
        user.setLastname( dto.getLastname() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );

        return user;
    }

    @Override
    public List<User> fromDTOs(List<RegUserDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtos.size() );
        for ( RegUserDto regUserDto : dtos ) {
            list.add( fromDTO( regUserDto ) );
        }

        return list;
    }
}

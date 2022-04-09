package uz.digitalone.houzingapp.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import uz.digitalone.houzingapp.dto.request.RegUserDto;
import uz.digitalone.houzingapp.dto.request.RoleDto;
import uz.digitalone.houzingapp.dto.response.UserDto;
import uz.digitalone.houzingapp.entity.Role;
import uz.digitalone.houzingapp.entity.User;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-04T09:35:53+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Amazon.com Inc.)"
)
*/
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
        userDto.setRoles( roleSetToRoleDtoSet( user.getRoles() ) );
        userDto.setAccountNonExpired( user.getAccountNonExpired() );
        userDto.setAccountNonLocked( user.getAccountNonLocked() );
        userDto.setCredentialsNonExpired( user.getCredentialsNonExpired() );
        userDto.setEnabled( user.getEnabled() );

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

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setName( role.getName() );

        return roleDto;
    }

    protected Set<RoleDto> roleSetToRoleDtoSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDto> set1 = new HashSet<RoleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleDto( role ) );
        }

        return set1;
    }
}

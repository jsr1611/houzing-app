package uz.digitalone.houzingapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import uz.digitalone.houzingapp.dto.request.RoleDto;
import uz.digitalone.houzingapp.entity.Role;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-26T21:21:00+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto fromEntity(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setName( entity.getName() );

        return roleDto;
    }

    @Override
    public List<RoleDto> fromEntities(List<Role> entities) {
        if ( entities == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( entities.size() );
        for ( Role role : entities ) {
            list.add( fromEntity( role ) );
        }

        return list;
    }

    @Override
    public Role fromDTO(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setName( dto.getName() );

        return role;
    }

    @Override
    public List<Role> fromDTOs(List<RoleDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( dtos.size() );
        for ( RoleDto roleDto : dtos ) {
            list.add( fromDTO( roleDto ) );
        }

        return list;
    }
}

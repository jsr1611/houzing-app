package uz.digitalone.houzingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.digitalone.houzingapp.dto.request.RoleCreateDto;
import uz.digitalone.houzingapp.entity.Role;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANS = Mappers.getMapper(RoleMapper.class);

    //Entity to Dto
    RoleCreateDto fromEntity(Role entity);

    //Entity to List dto
    List<RoleCreateDto> fromEntity(List<RoleCreateDto> entities);

}

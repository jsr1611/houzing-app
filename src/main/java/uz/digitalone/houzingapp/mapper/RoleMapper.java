package uz.digitalone.houzingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.digitalone.houzingapp.dto.request.RoleCreateDto;
import uz.digitalone.houzingapp.entity.Role;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    // Entity to DTO
    RoleCreateDto fromEntity(Role entity);

    // Entities to DTO's
    List<RoleCreateDto> fromEntities(List<Role> entities);

    // DTO to Entity
    Role fromDTO(RoleCreateDto dto);

    // DTO's to Entities
    List<Role> fromDTOs(List<RoleCreateDto> dtos);

}

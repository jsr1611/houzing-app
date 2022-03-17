package uz.digitalone.houzingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.digitalone.houzingapp.dto.request.RegUserDto;
import uz.digitalone.houzingapp.dto.response.UserDto;
import uz.digitalone.houzingapp.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Entity to DTO
    UserDto fromEntity(User user);

    // Entities to DTO's
    List<UserDto> fromEntities(List<User> users);

    // DTO to Entity
    User fromDTO(RegUserDto dto);

    // DTO's to Entities
    List<User> fromDTOs(List<RegUserDto> dtos);
}

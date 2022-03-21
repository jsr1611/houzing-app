package uz.digitalone.houzingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.digitalone.houzingapp.dto.request.AttachmentDto;
import uz.digitalone.houzingapp.entity.Attachment;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    AttachmentMapper INSTANCE = Mappers.getMapper(AttachmentMapper.class);

    //From entity to DTO
    AttachmentDto fromEntity(Attachment attachment);

    //From entities to DTOs
    List<AttachmentDto> fromEntities(List<Attachment> attachments);

    /**
     * From Entity set to DTO set
     * @param attachmentSet
     * @return
     */
    Set<AttachmentDto> fromEntitySet(Set<Attachment> attachmentSet);

    //From DTO to Entity
    Attachment fromDTO(AttachmentDto dto);

    //From DTOs to Entities
    List<Attachment> fromDTOs(List<AttachmentDto> dtos);

    /**
     * From DTO set to Entity Set
     * @param dtoSet
     * @return
     */
    Set<Attachment> fromDTOSet(Set<AttachmentDto> dtoSet);
}

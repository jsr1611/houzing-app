package uz.digitalone.houzingapp.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import uz.digitalone.houzingapp.dto.request.AttachmentDto;
import uz.digitalone.houzingapp.entity.Attachment;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-04T09:35:52+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Amazon.com Inc.)"
)
*/
@Component
public class AttachmentMapperImpl implements AttachmentMapper {

    @Override
    public AttachmentDto fromEntity(Attachment attachment) {
        if ( attachment == null ) {
            return null;
        }

        AttachmentDto attachmentDto = new AttachmentDto();

        attachmentDto.setImgPath( attachment.getImgPath() );

        return attachmentDto;
    }

    @Override
    public List<AttachmentDto> fromEntities(List<Attachment> attachments) {
        if ( attachments == null ) {
            return null;
        }

        List<AttachmentDto> list = new ArrayList<AttachmentDto>( attachments.size() );
        for ( Attachment attachment : attachments ) {
            list.add( fromEntity( attachment ) );
        }

        return list;
    }

    @Override
    public Set<AttachmentDto> fromEntitySet(Set<Attachment> attachmentSet) {
        if ( attachmentSet == null ) {
            return null;
        }

        Set<AttachmentDto> set = new HashSet<AttachmentDto>( Math.max( (int) ( attachmentSet.size() / .75f ) + 1, 16 ) );
        for ( Attachment attachment : attachmentSet ) {
            set.add( fromEntity( attachment ) );
        }

        return set;
    }

    @Override
    public Attachment fromDTO(AttachmentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Attachment attachment = new Attachment();

        attachment.setImgPath( dto.getImgPath() );

        return attachment;
    }

    @Override
    public List<Attachment> fromDTOs(List<AttachmentDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Attachment> list = new ArrayList<Attachment>( dtos.size() );
        for ( AttachmentDto attachmentDto : dtos ) {
            list.add( fromDTO( attachmentDto ) );
        }

        return list;
    }

    @Override
    public Set<Attachment> fromDTOSet(Set<AttachmentDto> dtoSet) {
        if ( dtoSet == null ) {
            return null;
        }

        Set<Attachment> set = new HashSet<Attachment>( Math.max( (int) ( dtoSet.size() / .75f ) + 1, 16 ) );
        for ( AttachmentDto attachmentDto : dtoSet ) {
            set.add( fromDTO( attachmentDto ) );
        }

        return set;
    }
}

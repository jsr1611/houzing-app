package uz.digitalone.houzingapp.service.impl;

/*
 * project:  houzing-app
 * author:   Jumanazar Said
 * created:  25/02/2022 2:57 PM
 */


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.request.AttachmentDto;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.entity.Attachment;
import uz.digitalone.houzingapp.repository.AttachmentRepository;
import uz.digitalone.houzingapp.service.AttachmentService;

import java.util.*;

@RequiredArgsConstructor
@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Override
    public HttpEntity<?> save(AttachmentDto dto) {
        return null;
    }

    @Override
    public Attachment create(String imgPath) {
        return  attachmentRepository.save(new Attachment(imgPath));
    }

    @Override
    public Set<Attachment> createList(Set<AttachmentDto> dto) {
        Set<Attachment> attachmentList = new HashSet<>();
        if(dto != null){
            for (AttachmentDto attachmentDto : dto) {
                Attachment attachment = create(attachmentDto.getImgPath());
              attachmentList.add(attachment);
            }
        }
        return attachmentList;
    }

    @Override
    public HttpEntity<?> findAll(Pageable pageable) {
        Response response = null;
        Page<Attachment> attachmentPages = attachmentRepository.findAll(pageable);
        List<Attachment> attachmentAllContent = attachmentPages.getContent();

        if(attachmentAllContent.size() == 0){
            response= new Response(true, "No attachments were found");
        }
        else {
            response = new Response (true, "Attachment list", new ArrayList<>(attachmentAllContent));
        }
        response.getMap().put("size", attachmentPages.getSize());
        response.getMap().put("total_elements", attachmentPages.getTotalElements());
        response.getMap().put("total_pages", attachmentPages.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @Override
    public Attachment findById(Long id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        return optionalAttachment.orElse(null);
    }

    @Override
    public HttpEntity<?> edit(Long id, String imgPath) {
        Response response = null;
        Attachment attachment = findById(id);
        if(attachment != null){
            attachment.setImgPath(imgPath);
            attachment = attachmentRepository.save(attachment);
            response = new Response(true, "Successfully updated.", attachment);
        }else {
            response = new Response(false, "Attachment with id {"+id+"} not found.");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> delete(Long id) {
        Response response = null;
        Attachment attachment = findById(id);
        if(attachment!=null){
            attachmentRepository.delete(attachment);
            response = new Response(true, "Successfully deleted.");
        }
        else {
            response = new Response(false, "Attachment with id {"+id+"} not found.");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public Set<Attachment> update(Set<Attachment> attachments, Set<AttachmentDto> attachmentDtoSet) {
        Set<Attachment> updatedAttachments = new HashSet<>();
        boolean attachmentExists = false;
        // TODO: 3/12/22 Logic should be updated, because this is inefficient.
//        try {
            if(attachments != null && attachments.size()>0) {
            // deleted attachments
                for (Attachment attachment : attachments) {
                    for (AttachmentDto attachmentDto : attachmentDtoSet) {
                        if(attachment.getImgPath().equals(attachmentDto.getImgPath())){
                            attachmentExists = true;
                            updatedAttachments.add(attachment);
                            break;
                        }
                    }
                    if(!attachmentExists){
                        try {
                            attachmentRepository.delete(attachment);            // Error here
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    else {
                        attachmentExists = false;
                    }
                }

                // new attachments
                for (AttachmentDto attachmentDto : attachmentDtoSet) {
                    attachmentExists = false;
                    for (Attachment attachment : attachments) {
                        if(attachmentDto.getImgPath().equals(attachment.getImgPath())){
                            attachmentExists = true;
                            break;
                        }
                    }
                    if(!attachmentExists){
                        Attachment attachment = new Attachment(attachmentDto.getImgPath());
                        Attachment updatedAttachment = attachmentRepository.save(attachment);
                        updatedAttachments.add(updatedAttachment);
                    }
                }
            }
        return updatedAttachments;
    }
}

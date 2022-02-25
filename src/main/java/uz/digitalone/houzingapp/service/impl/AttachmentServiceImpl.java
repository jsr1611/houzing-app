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
import uz.digitalone.houzingapp.entity.Category;
import uz.digitalone.houzingapp.repository.AttachmentRepository;
import uz.digitalone.houzingapp.service.AttachmentService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Set<Attachment> createList(AttachmentDto dto) {
        Set<Attachment> attachmentList = new HashSet<>();
        if(dto != null){
            for (String imgPath : dto.getImgPathList()) {
              Attachment attachment =  create(imgPath);
              attachmentList.add(attachment);
            }
        }
        return attachmentList;
    }

    @Override
    public HttpEntity<?> findAll(Pageable pageable) {
        Response response = new Response();
        Page<Attachment> attachmentPages = attachmentRepository.findAll(pageable);
        List<Attachment> attachmentAllContent = attachmentPages.getContent();

        response.setSuccess(true);
        if(attachmentAllContent.size() == 0){
            response.setMessage("No attachments were found");
        }
        else {
            response.setMessage("Attachment list");
        }
        response.setDataList(new ArrayList<>(attachmentAllContent));
        response.getMap().put("size", attachmentPages.getSize());
        response.getMap().put("total_elements", attachmentPages.getTotalElements());
        response.getMap().put("total_pages", attachmentPages.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @Override
    public Attachment findById(Long id) {
        return null;
    }

    @Override
    public HttpEntity<?> edit(Long id, AttachmentDto dto) {
        return null;
    }

    @Override
    public HttpEntity<?> delete(Long id) {
        return null;
    }
}

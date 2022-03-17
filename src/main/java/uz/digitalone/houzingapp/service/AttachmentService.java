package uz.digitalone.houzingapp.service;

/*
 * project:  houzing-app
 * author:   Jumanazar Said
 * created:  25/02/2022 2:55 PM
 */


import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.request.AttachmentDto;
import uz.digitalone.houzingapp.entity.Attachment;

import java.util.Set;

@Service
public interface AttachmentService {
    HttpEntity<?> save(AttachmentDto dto);

    Attachment create(String imgPath);

    Set<Attachment> createList(AttachmentDto dto);

    HttpEntity<?> findAll(Pageable pageable);

    Attachment findById(Long id);

    HttpEntity<?> edit(Long id, AttachmentDto dto);

    HttpEntity<?> delete(Long id);
}

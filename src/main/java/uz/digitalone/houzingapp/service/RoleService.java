package uz.digitalone.houzingapp.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.request.RoleCreateDto;
import uz.digitalone.houzingapp.entity.Role;

import java.util.List;

@Service
public interface RoleService {
    HttpEntity<?> saveRole(RoleCreateDto dto);

    HttpEntity<?> getOneById(Long id);

    HttpEntity<?> findAll(Pageable pageable);

    Role findById(Long roleId);

    HttpEntity<?> updateRole(Long roleId, RoleCreateDto dto);

    HttpEntity<?> removeRoleById(Long roleId);

    HttpEntity<?> findByIdMapper(Long id);
}

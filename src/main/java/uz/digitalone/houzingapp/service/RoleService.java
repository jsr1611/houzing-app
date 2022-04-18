package uz.digitalone.houzingapp.service;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import uz.digitalone.houzingapp.dto.request.RoleDto;
import uz.digitalone.houzingapp.entity.Role;

public interface RoleService {
    HttpEntity<?> saveRole(RoleDto dto);

    HttpEntity<?> getOneById(Long id);

    HttpEntity<?> findAll(Pageable pageable);

    Role findById(Long roleId);

    HttpEntity<?> updateRole(Long roleId, RoleDto dto);

    HttpEntity<?> removeRoleById(Long roleId);
}

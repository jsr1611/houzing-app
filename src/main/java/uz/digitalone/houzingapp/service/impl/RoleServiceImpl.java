package uz.digitalone.houzingapp.service.impl;

/*
 * project:  houzing-app
 * author:   Jumanazar Said
 * created:  25/02/2022 10:03 AM
 */


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.request.RoleDto;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.entity.Role;
import uz.digitalone.houzingapp.mapper.RoleMapper;
import uz.digitalone.houzingapp.repository.RoleRepository;
import uz.digitalone.houzingapp.service.RoleService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    @Override
    public HttpEntity<?> saveRole(RoleDto dto) {
        Role role = new Role(
                dto.getName()
        );
        role =  roleRepository.save(role);
        Response response = new Response(true, "Successfully created.", roleMapper.fromEntity(role));
        return ResponseEntity.ok(response);
    }

    /**
     * Get Role by id
     * @param id roleId
     * @return Role DTO object
     */
    @Override
    public HttpEntity<?> getOneById(Long id) {
        Response response = null;
        Role role = findById(id);
        if(role != null){
            response = new Response(true, "Role", roleMapper.fromEntity(role));
        }else {
            response = new Response(false, "Role not found with id " + id);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    @Override
    public HttpEntity<?> findAll(Pageable pageable) {
        Page<Role> roles = roleRepository.findAll(pageable);
        List<Role> roleList = roles.getContent();
        List<RoleDto> result = null;
        if(roleList != null && roleList.size() > 0)
            result = roleMapper.fromEntities(roleList);
        Response response = new Response(true, "Role List", result);
        return ResponseEntity.ok(response);
    }


    @Override
    public Role findById(Long roleId) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        return roleOptional.orElse(null);
    }


    @Override
    public HttpEntity<?> updateRole(Long roleId, RoleDto dto) {
        Role role = findById(roleId);
        Response response = null;
        if (role != null) {
            if (!role.getName().equals(dto.getName())) {
                role.setName(dto.getName());
            }
            role = roleRepository.save(role);
            response = new Response(true, "Successfully updated.", roleMapper.fromEntity(role));
        }
        else {
            response = new Response(false, "Role not found with id" + roleId);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    @Override
    public HttpEntity<?> removeRoleById(Long roleId) {
        Role role = findById(roleId);
        Response response = null;
        if(role != null){
            roleRepository.deleteById(roleId);
            response = new Response(true, "Successfully deleted.");
        }else {
            response = new Response(false, "Role not found with id " + roleId);
        }

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}

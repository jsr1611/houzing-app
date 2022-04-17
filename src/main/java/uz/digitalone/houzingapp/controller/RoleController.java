package uz.digitalone.houzingapp.controller;

/*
 * project:  houzing-app
 * author:   Jumanazar Said
 * created:  25/02/2022 10:09 AM
 */


import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.digitalone.houzingapp.dto.request.RoleDto;
import uz.digitalone.houzingapp.service.RoleService;

@RequestMapping("/api/v1/roles")
@RestController
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;


    @ApiOperation(value = "Make GET request to get Role By Id")
    @GetMapping("/{roleId}")
    public HttpEntity<?> getById(@PathVariable Long roleId){
        return roleService.getOneById(roleId);
    }

    @PutMapping("/{role_id}")
    public HttpEntity<?> edit(@PathVariable Long role_id, @RequestBody RoleDto dto){
        return roleService.updateRole(role_id, dto);
    }
}

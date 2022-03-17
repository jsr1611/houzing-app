package uz.digitalone.houzingapp.controller;

/*
 * project:  houzing-app
 * author:   Jumanazar Said
 * created:  25/02/2022 10:09 AM
 */


import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.digitalone.houzingapp.service.RoleService;

@RequestMapping("/api/v1/roles")
@RestController
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @ApiOperation(value = "Make GET operation request to get By Role Id")
    @GetMapping("/{id}")
    public HttpEntity<?> findById(@PathVariable Long id){
        return roleService.findByIdMapper(id);
    }

}

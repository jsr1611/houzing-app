package uz.digitalone.houzingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.digitalone.houzingapp.dto.request.LoginDto;
import uz.digitalone.houzingapp.dto.request.RegUserDto;
import uz.digitalone.houzingapp.dto.request.RoleDto;
import uz.digitalone.houzingapp.service.RoleService;
import uz.digitalone.houzingapp.service.impl.MyUserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public")
public class PublicController {

    private final MyUserService userService;
    private final RoleService roleService;

    @PostMapping("/auth/register")
    public HttpEntity<?> register(@Valid @RequestBody RegUserDto dto, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(userService.getErrors(errors));
        }
        return userService.register(dto);
    }

    @PostMapping("/auth/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDto dto, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(userService.getErrors(errors));
        }
        return userService.login(dto);
    }


    @PostMapping("/roles")
    public HttpEntity<?> createRole(@Valid @RequestBody RoleDto dto, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(userService.getErrors(errors));
        }
        return roleService.saveRole(dto);
    }
}

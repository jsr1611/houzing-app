package uz.digitalone.houzingapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import uz.digitalone.houzingapp.dto.request.RefreshTokenRequest;
import uz.digitalone.houzingapp.dto.request.LoginRequest;
import uz.digitalone.houzingapp.dto.request.RegUserDto;
import uz.digitalone.houzingapp.dto.request.RoleDto;
import uz.digitalone.houzingapp.dto.response.AuthenticationResponse;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.service.RoleService;
import uz.digitalone.houzingapp.service.impl.MyUserService;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public")
@Slf4j
public class PublicController {

    private final MyUserService userService;
    private final RoleService roleService;

    @PostMapping("/auth/register")
    public HttpEntity<?> register(@Valid @RequestBody RegUserDto dto, @ApiIgnore Errors errors){
        log.info("/auth/register: " + dto.toString());
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(userService.getErrors(errors));
        }
        return userService.register(dto);
    }

    @GetMapping("/verification/{token}")
    public HttpEntity<?> accountVerification(@PathVariable("token") String token) {
        log.info("/verification/" + token);
        return userService.verifyAccount(token);
    }

    @PostMapping("/auth/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginRequest dto,@ApiIgnore Errors errors){
        log.info("/auth/login: " + dto.toString());
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(userService.getErrors(errors));
        }
        Response response = userService.findUser(dto);
        if(!response.isSuccess())
            return ResponseEntity.ok(response);
        return userService.login(dto);
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest request){
       log.info("/logout: " + request.toString());
        return userService.logout(request);
    }

    @PostMapping("/refresh/token")
    public HttpEntity<AuthenticationResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest dto) {
        log.info("/refresh/token: " + dto.toString());
        return userService.refreshToken(dto);
    }

    @PostMapping("/roles")
    public HttpEntity<?> createRole(@Valid @RequestBody RoleDto dto,@ApiIgnore Errors errors){
        log.info("/roles: " + dto.toString());
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(userService.getErrors(errors));
        }
        return roleService.saveRole(dto);
    }

    @PostMapping("/forgot-password")
    public HttpEntity<?> forgotPassword(@RequestParam String email) {
        log.info("/forgot-password: " + email);
        return userService.forgotPassword(email);

    }

    @PutMapping("/reset-password")
    public HttpEntity<?> resetPassword(@RequestParam String token,
                                @RequestParam String password) {
        log.info("/reset-password: " + token + ", " + password);
        return userService.resetPassword(token, password);
    }
}

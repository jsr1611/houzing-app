package uz.digitalone.houzingapp.controller;

import lombok.RequiredArgsConstructor;
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
    public HttpEntity<?> register(@Valid @RequestBody RegUserDto dto, @ApiIgnore Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(userService.getErrors(errors));
        }
        return userService.register(dto);
    }

    @GetMapping("/verification/{token}")
    public HttpEntity<?> accountVerification(@PathVariable("token") String token) {
        return userService.verifyAccount(token);

    }

//    @PostMapping("/verification/{token}")
//    public ResponseEntity<?> verification(@PathVariable String token){
//        userService.verification(token);
//        return ResponseEntity.ok().body("Account active");
//    }

    @PostMapping("/auth/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginRequest dto,@ApiIgnore Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(userService.getErrors(errors));
        }
        return userService.login(dto);
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest request){
       return userService.logout(request);
    }

    @PostMapping("/refresh/token")
    public HttpEntity<AuthenticationResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest dto) {
        return userService.refreshToken(dto);
    }

    @PostMapping("/roles")
    public HttpEntity<?> createRole(@Valid @RequestBody RoleDto dto,@ApiIgnore Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(userService.getErrors(errors));
        }
        return roleService.saveRole(dto);
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {

        String response = userService.forgotPassword(email);

        if (!response.startsWith("Invalid")) {
            response = "http://localhost:8080/reset-password?token = " + response;
        }
        return response;
    }

    @PutMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String password) {

        return userService.resetPassword(token, password);
    }
}

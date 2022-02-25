package uz.digitalone.houzingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import uz.digitalone.houzingapp.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/25/2022
 * Time: 4:25 PM
 */

@RestController
@RequestMapping("/me")
public class MeController {

    @GetMapping
    public ResponseEntity<?> getLoggedUserInfo(@ApiIgnore Authentication authentication) {
        if (authentication != null) {
            User user;
            Map<String, Object> result = new HashMap<>();
            if (authentication.getPrincipal() instanceof User) {
                user = (User) authentication.getPrincipal();
                result.put("id", user.getId());
                result.put("email", user.getEmail());
                result.put("authorities", user.getAuthorities());
            }
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token Expired!");
    }
}

package uz.digitalone.houzingapp.config.aop;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)         //
@AuthenticationPrincipal
public @interface CurrentUser {
}

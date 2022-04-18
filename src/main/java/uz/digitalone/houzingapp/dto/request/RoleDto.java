package uz.digitalone.houzingapp.dto.request;

/*
 * project:  houzing-app
 * author:   Jumanazar Said
 * created:  25/02/2022 10:01 AM
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    @NotNull
    @NotBlank
    private String name;
}

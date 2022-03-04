package uz.digitalone.houzingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.digitalone.houzingapp.entity.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegUserDto {

    @NotNull(message = "firstname field cannot be null")
    @NotBlank(message = "firstname field cannot blank")
    @Size(min = 3, max = 50, message = "firstname cannot be less than 3 letters or more than 50 letters")
    private String firstname;

    @NotNull(message = "lastname field cannot be null")
    @NotBlank(message = "lastname field cannot blank")
    @Size(min = 3, max = 50, message = "lastname cannot be less than 3 letters or more than 50 letters")
    private String lastname;


    @NotNull(message = "email field cannot be null")
    @Email(message = "invalid email address")
    private String email;

    @NotNull(message = "password field cannot be null")
    @NotBlank(message = "password field cannot be blank")
    private String password;

    @NotNull(message = "role id list cannot be null")
    private Set<Long> roleIdSet;
}

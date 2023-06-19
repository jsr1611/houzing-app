package uz.digitalone.houzingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegUserDto {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String firstname;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String lastname;


    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotEmpty
    private Set<Long> roleIdSet = new HashSet<>();

    @Override
    public String toString() {
        return "RegUserDto{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleIdSet=" + roleIdSet +
                '}';
    }
}

package uz.digitalone.houzingapp.dto.request;

/*
 * project:  houzing-app
 * author:   Jumanazar Said
 * created:  25/02/2022 2:52 PM
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDto {
    @Size(max = 255, message = "image path must not exceed 255 characters")
    private String imgPath;
}

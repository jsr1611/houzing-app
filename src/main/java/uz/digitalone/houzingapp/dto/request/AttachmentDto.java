package uz.digitalone.houzingapp.dto.request;

/*
 * project:  houzing-app
 * author:   Jumanazar Said
 * created:  25/02/2022 2:52 PM
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDto {
    private Set<String> imgPathList;
}

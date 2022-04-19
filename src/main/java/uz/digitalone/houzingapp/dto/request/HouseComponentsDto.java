package uz.digitalone.houzingapp.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HouseComponentsDto {

    private boolean tv = false;
    private boolean furniture = false;
    private boolean airCondition = false;
    private boolean gasStove = false;
    private boolean internet = false;
    private boolean courtyard = false;
    private String additional;
}

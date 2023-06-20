package uz.digitalone.houzingapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class HouseComponentsDto {

    private Long id;
    private boolean tv;
    private boolean furniture;
    private boolean airCondition;
    private boolean gasStove ;
    private boolean internet ;
    private boolean courtyard;
    private String additional;
}

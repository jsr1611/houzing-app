package uz.digitalone.houzingapp.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomeAmenitiesDto {

    private boolean school = false;
    private boolean park = false;
    private boolean parking = false;
    private boolean superMarket = false;
    private boolean market = false;
    private boolean stadium = false;
    private boolean subway = false;
    private boolean garden = false;
    private boolean busStop = false;
    private String additional;

}

package uz.digitalone.houzingapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class HomeAmenitiesResponseDto {

    private Long id;
    private boolean school ;
    private boolean park;
    private boolean parking;
    private boolean superMarket;
    private boolean market ;
    private boolean stadium;
    private boolean subway ;
    private boolean garden ;
    private boolean busStop;
    private String additional;
}

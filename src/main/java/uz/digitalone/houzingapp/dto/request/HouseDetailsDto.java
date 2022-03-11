package uz.digitalone.houzingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDetailsDto {

    @NotNull
    @Positive
    @Range(min = 1, max = 999)
    private Integer room;
    private Integer bath;
    private boolean hasGarage;
    private Double area;
}

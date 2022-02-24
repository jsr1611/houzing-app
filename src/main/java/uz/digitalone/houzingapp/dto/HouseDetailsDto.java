package uz.digitalone.houzingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDetailsDto {

    private Integer room;
    private Integer bath;
    private boolean hasGarage;
    private Double area;
}

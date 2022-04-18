package uz.digitalone.houzingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.digitalone.houzingapp.entity.template.AbcEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeAmenities extends AbcEntity {

    private boolean school;
    private boolean park;
    private boolean parking;
    private boolean superMarket;
    private boolean market;
    private boolean stadium;
    private boolean subway;
    private boolean garden;
    private boolean busStop;
    private String additional; // qo'shimcha

}

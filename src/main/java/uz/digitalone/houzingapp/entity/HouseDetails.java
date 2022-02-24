package uz.digitalone.houzingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.digitalone.houzingapp.entity.template.AbcEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "house_details")
public class HouseDetails extends AbcEntity {

    @Column(nullable = false)
    private Integer room;

    @Column(nullable = false)
    private Integer bath;

    @Column(nullable = false)
    private boolean hasGarage;

    @Column(nullable = false)
    private Double area;
}

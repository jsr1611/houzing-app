package uz.digitalone.houzingapp.entity;

import lombok.*;
import org.hibernate.Hibernate;
import uz.digitalone.houzingapp.entity.template.AbcEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "location")
public class Location extends AbcEntity {

    @Column(name = "longitude", nullable = false, unique = true)
    private Double longitude;

    @Column(name = "latitude", nullable = false, unique = true)
    private Double latitude;

}

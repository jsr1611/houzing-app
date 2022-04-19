package uz.digitalone.houzingapp.entity.auth;

import lombok.*;
import org.hibernate.Hibernate;
import uz.digitalone.houzingapp.entity.Location;
import uz.digitalone.houzingapp.entity.template.AbcEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends AbcEntity {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;*/

    private String refreshToken;

    private Instant expirationTime;


}

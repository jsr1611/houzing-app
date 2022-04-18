package uz.digitalone.houzingapp.entity.auth;

import lombok.*;
import uz.digitalone.houzingapp.entity.User;
import uz.digitalone.houzingapp.entity.template.AbcEntity;

import javax.persistence.*;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken extends AbcEntity {

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    private String token;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private Instant expirationData;

}

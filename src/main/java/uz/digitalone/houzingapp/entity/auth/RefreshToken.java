package uz.digitalone.houzingapp.entity.auth;

import lombok.*;
import org.hibernate.Hibernate;
import uz.digitalone.houzingapp.entity.template.AbcEntity;

import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends AbcEntity {

    private String refreshToken;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RefreshToken that = (RefreshToken) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

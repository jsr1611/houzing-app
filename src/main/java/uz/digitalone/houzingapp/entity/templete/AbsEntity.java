package uz.digitalone.houzingapp.entity.templete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class AbsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AbsEntity abcEntity = (AbsEntity) o;
        return id != null && Objects.equals(id, abcEntity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

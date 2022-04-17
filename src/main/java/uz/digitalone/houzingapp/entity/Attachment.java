package uz.digitalone.houzingapp.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.digitalone.houzingapp.entity.template.AbcEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "attachment")
@SQLDelete(sql = "UPDATE attachment SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Attachment extends AbcEntity {

    @Column(name = "img_path", length = 1000)
    private String imgPath;

    @Column(name =  "deleted")
    private  boolean deleted;

    public Attachment(String imgPath, boolean deleted) {
        this.imgPath = imgPath;
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Attachment that = (Attachment) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
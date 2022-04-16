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

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "attachment")
@SQLDelete(sql = "UPDATE attachment SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Attachment extends AbcEntity {

    @Column(name = "img_path", length = 1000)
    private String imgPath;

    @Column(name =  "deleted")
    private  boolean deleted;

}
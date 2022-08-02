package uz.digitalone.houzingapp.entity;

import javassist.bytecode.annotation.IntegerMemberValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.validation.annotation.Validated;
import uz.digitalone.houzingapp.entity.template.AbcEntity;

import javax.mail.search.IntegerComparisonTerm;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.time.LocalDate;

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


    private Integer garage;

    @Column(nullable = false)
    private Double area;

    private Integer beds;

    @Column(nullable = true)
    private Integer yearBuilt;
}

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
public class HouseComponents extends AbcEntity {

    private boolean tv; // televizor
    private boolean furniture;   // mebel
    private boolean airCondition; // konditsioner
    private boolean gasStove;  // gaz pechi
    private boolean internet;  // wifi etc.
    private boolean courtyard; // hovli
    private String additional; // qo'shimcha
}

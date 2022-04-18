package uz.digitalone.houzingapp.entity;

import lombok.*;
import uz.digitalone.houzingapp.entity.template.AbcEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "houses")
public class House extends AbcEntity implements Serializable {
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "house_details_id")
    private HouseDetails houseDetails;

    private Double price;

    private Double salePrice;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private String address;

    private String city;

    private String region;

    private String country;

    private String zipCode;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Attachment> attachments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Boolean status;

    @Column(nullable = true)
    private Boolean favorite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "home_amenities_id", referencedColumnName = "id")
    private HomeAmenities homeAmenities;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "house_components_id", referencedColumnName = "id")
    private HouseComponents houseComponents;
}
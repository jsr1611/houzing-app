package uz.digitalone.houzingapp.entity;

import lombok.*;
import org.hibernate.Hibernate;
import lombok.*;
import uz.digitalone.houzingapp.entity.template.AbcEntity;
import uz.digitalone.houzingapp.enums.Status;

import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
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

    private Boolean isSold;

    @Column(nullable = true)
    private Boolean favorite;

    @Enumerated(EnumType.STRING)
    private Status status;

    public House(Long id, Timestamp createdAt,
                 Timestamp updateAt, Long createdBy,
                 Long updateBy, String name, String description,
                 User user, HouseDetails houseDetails, Double price,
                 Double salePrice, Location location, String address,
                 String city, String region, String country, String zipCode,
                 Set<Attachment> attachments, Category category, Boolean isSold,
                 Boolean favorite, Status status) {
        super(id, createdAt, updateAt, createdBy, updateBy);
        this.name = name;
        this.description = description;
        this.user = user;
        this.houseDetails = houseDetails;
        this.price = price;
        this.salePrice = salePrice;
        this.location = location;
        this.address = address;
        this.city = city;
        this.region = region;
        this.country = country;
        this.zipCode = zipCode;
        this.attachments = attachments;
        this.category = category;
        this.isSold = isSold;
        this.favorite = favorite;
        this.status = status;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "home_amenities_id", referencedColumnName = "id")
    private HomeAmenities homeAmenities;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "house_components_id", referencedColumnName = "id")
    private HouseComponents houseComponents;
}

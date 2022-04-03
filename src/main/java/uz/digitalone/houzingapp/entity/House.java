package uz.digitalone.houzingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;
import uz.digitalone.houzingapp.entity.template.AbcEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "houses")
public class House extends AbcEntity {
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "house_details_id")
    private HouseDetails houseDetails;

    private Double price;

    private Double salePrice;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "location_id")
    private Location location;

    private String address;

    private String city;

    private String region;

    private String country;

    private String zipCode;

    @OneToMany(cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Set<Attachment> attachments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Boolean status;

    @Column(nullable = true)
    private Boolean favorite;

    public House(String name, String description,
                 User user,
                 HouseDetails houseDetails,
                 Double price, Double salePrice,
                 Location location,
                 String address, String city,
                 String region, String country, String zipCode,
                 Set<Attachment> attachmentList,
                 Category category,
                 Boolean favorite) {
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
        this.attachments = attachmentList;
        this.category = category;
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        House house = (House) o;
        return getId() != null && Objects.equals(getId(), house.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

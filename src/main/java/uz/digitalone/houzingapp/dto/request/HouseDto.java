package uz.digitalone.houzingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.RequiredTypes;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import uz.digitalone.houzingapp.entity.Attachment;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDto {

    @NotNull
    @NotBlank
    private String name;
    private String description;
    @Valid
    private HouseDetailsDto houseDetails;
    @NotNull
    private Double price;
    private Double salePrice;
    @Valid
    private LocationDto locations;

    @NotNull
    @NotBlank
    private String address;
    private String city;
    private String region;
    private String country;
    private String zipCode;
    @Valid
    private Set<AttachmentDto> attachments;
    private Long categoryId;
    private Boolean status;

    private HomeAmenitiesDto homeAmenitiesDto;
    private HouseComponentsDto componentsDto;
}

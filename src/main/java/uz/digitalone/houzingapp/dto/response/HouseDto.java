package uz.digitalone.houzingapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.digitalone.houzingapp.dto.request.AttachmentDto;
import uz.digitalone.houzingapp.dto.request.HouseDetailsDto;
import uz.digitalone.houzingapp.dto.request.LocationDto;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDto {
    private Long id;
    private String name;
    private String description;
    private UserDto user;
    private HouseDetailsDto houseDetails;
    private Double price;
    private Double salePrice;
    private LocationDto location;
    private String address;
    private String city;
    private String region;
    private String country;
    private String zipCode;
    private Set<AttachmentDto> attachments;
    private CategoryDto category;
    private Boolean status;
    private Boolean favorite;
    private String isSolid;
}

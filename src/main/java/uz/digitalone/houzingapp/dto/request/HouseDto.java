package uz.digitalone.houzingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.digitalone.houzingapp.entity.Attachment;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDto {
    private String name;
    private String description;
    private HouseDetailsDto houseDetailsDto;
    private Double price;
    private Double salePrice;
    private LocationDto locationDto;
    private String address;
    private String city;
    private String region;
    private String country;
    private String zipCode;
    private AttachmentDto attachmentDto;
    private Long categoryId;
}

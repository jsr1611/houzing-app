package uz.digitalone.houzingapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.digitalone.houzingapp.entity.Attachment;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDto {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;
    private String description;
    @Valid
    private HouseDetailsDto houseDetailsDto;
    @NotNull
    private Double price;
    private Double salePrice;
    @Valid
    private LocationDto locationDto;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 100)
    private String address;
    private String city;
    private String region;
    private String country;
    private String zipCode;
    @Valid
    private AttachmentDto attachmentDto;
    private Long categoryId;
}

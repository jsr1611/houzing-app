package uz.digitalone.houzingapp.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import uz.digitalone.houzingapp.dto.request.HouseDto;
import uz.digitalone.houzingapp.service.HouseService;
import uz.digitalone.houzingapp.service.impl.MyUserService;
import uz.digitalone.houzingapp.utils.ApiPageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/houses")
public class HouseController {

    private final HouseService houseService;
    private final MyUserService userService;

    @ApiOperation(value = "Ushbu API yangi uy e`lonini qo`shish uchun ishlatiladi")
    @PostMapping
    public HttpEntity<?> create(@RequestBody HouseDto dto, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(userService.getErrors(errors));
        }
        return houseService.create(dto);
    }

    @ApiPageable
    @GetMapping
    public HttpEntity<?> getAll(
                        @RequestParam(value = "house_name", required = false) String houseName,
                        @RequestParam(value = "first_name", required = false) String firstName,
                        @RequestParam(value = "last_name", required = false) String lastName,
                        @RequestParam(value = "room", required = false) Integer room,
                        @RequestParam(value = "min_price",required = false) Double minPrice,
                        @RequestParam(value = "max_price",required = false) Double maxPrice,
                        @RequestParam(value = "address",required = false) String address,
                        @RequestParam(value = "city",required = false) String city,
                        @RequestParam(value = "region",required = false) String region,
                        @RequestParam(value = "country",required = false) String country,
                        @RequestParam(value = "zip_code",required = false) String zipCode,
                        @ApiIgnore Pageable pageable){
        return houseService.findAll(
                houseName, firstName, lastName, room,
                minPrice, maxPrice, address,
                city, region, country, zipCode, pageable
        );
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @RequestBody HouseDto dto, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(userService.getErrors(errors));
        }
        return houseService.edit(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        return houseService.delete(id);

    }

}

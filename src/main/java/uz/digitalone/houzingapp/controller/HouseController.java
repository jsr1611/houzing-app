package uz.digitalone.houzingapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/houses")
public class HouseController {

    private final HouseService houseService;
    private final MyUserService userService;

    @ApiOperation(value = "Ushbu API yangi uy e`lonini qo`shish uchun ishlatiladi")
    @PostMapping
    public HttpEntity<?> create(@Valid @RequestBody HouseDto dto, @ApiIgnore Errors errors){
        log.info(userService.getUserIdInfo() +  "POST /houses -> create: " + dto);
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(userService.getErrors(errors));
        }
        return houseService.create(dto);
    }

    @ApiOperation("Ushbu API filterlar bo`yicha va sahifalarga ajratilgan holda barcha uy e`lonlarini chaqirib olishga mo`ljallangan.")
    @ApiPageable
    @GetMapping("/list")
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
                        @RequestParam(value = "category_id", required = false) Long categoryId,
                        @ApiIgnore Pageable pageable){

        log.info(userService.getUserIdInfo() +  "GET /houses/list ");
        return houseService.findAll(
                houseName, firstName, lastName, room,
                minPrice, maxPrice, address,
                city, region, country, zipCode, categoryId, pageable
        );
    }

    @ApiOperation("Ushbu API mavjud uy e`lonini ID bo`yicha qidirib, yangilash/o'zgartirish uchun mo`ljallangan. ")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @RequestBody HouseDto dto,@ApiIgnore Errors errors){
        log.info(userService.getUserIdInfo() +  "PUT /houses/" + id + " dto: " + dto);
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(userService.getErrors(errors));
        }
        return houseService.edit(id, dto);
    }

    @ApiOperation("Ushbu API mavjud uy e`lonini ID bo`yicha qidirib ma`lumotlar omboridan o'chirib tashlashga mo`ljallangan.")
    @DeleteMapping("/{house_id}")
    public HttpEntity<?> delete(@PathVariable Long house_id){
        log.info(userService.getUserIdInfo() +  "DELETE /houses/ " + house_id);
        return houseService.delete(house_id);

    }

    @GetMapping("/id/{house_id}")
    public HttpEntity<?> getOneById(@PathVariable Long house_id){
        log.info(userService.getUserIdInfo() +  "GET /houses/id/" +  house_id);
        return houseService.findOneById(house_id);
    }

    @ApiOperation(value = "Ushbu API sistemaga login qilgan userga tegishli house larni qayataradi")
    @ApiPageable
    @GetMapping("/me")
    public HttpEntity<?> getMyHouseList(@RequestParam(value = "house_name", required = false) String houseName,
                                        @RequestParam(value = "status", required = false) Boolean status,
                                        @RequestParam(value = "created_at", required = false) LocalDateTime createdAt,
                                        @ApiIgnore Pageable pageable){
        log.info(userService.getUserIdInfo() +  "GET /houses/me");
        return houseService.findMyHouses(houseName, status, createdAt, pageable);
    }

    @ApiOperation(value = "Ushbu API CategoryId orqali Shu Categoryga Tegishli uylarni qaytaradi")
    @ApiPageable
    @GetMapping("/category/{category_id}")
    public HttpEntity<?> getAllHousesByCategoryId(@PathVariable Long category_id, Pageable pageable){
        log.info(userService.getUserIdInfo() +  "GET /houses/category/" + category_id);
        return houseService.getAllHousesByCategoryId(category_id, pageable);
    }

    @PutMapping("/addFavourite/{id}")
    public HttpEntity<?> addFavourite(@PathVariable Long id, @RequestParam Boolean favourite){
        log.info(userService.getUserIdInfo() +  "GET /houses/addFavorite/" + id + "favorite: " + favourite);
        return houseService.addFavourite(id, favourite);
    }

    @GetMapping("/getAll/favouriteList")
    public HttpEntity<?> getAllFavourite(){
        log.info(userService.getUserIdInfo() +  "GET /houses/getAll/favouriteList");
        return houseService.getAllFavourite();
    }

}

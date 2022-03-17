package uz.digitalone.houzingapp.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import uz.digitalone.houzingapp.dto.request.HouseDto;
import uz.digitalone.houzingapp.service.HouseService;
import uz.digitalone.houzingapp.utils.ApiPageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/houses")
public class HouseController {

    private final HouseService houseService;

    @ApiOperation(value = "Ushbu API yangi uy e`lonini qo`shish uchun ishlatiladi")
    @PostMapping
    public HttpEntity<?> create(@RequestBody HouseDto dto){
        return houseService.create(dto);
    }

    @ApiPageable
    @GetMapping
    public HttpEntity<?> getAll(@ApiIgnore Pageable pageable){
        return houseService.findAll(pageable);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id, @RequestBody HouseDto dto){
        return houseService.edit(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        return houseService.delete(id);

    }

    @ApiOperation(value = "Ushbu API user id ga tegishli house lar Listni qayataradi")
    @GetMapping("/{user_id}")
    public HttpEntity<?> userIdForListHouse(@PathVariable Long user_id){
        return houseService.findByList(user_id);
    }

}

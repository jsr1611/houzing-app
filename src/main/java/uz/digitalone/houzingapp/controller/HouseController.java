package uz.digitalone.houzingapp.controller;

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

    private HouseService houseService;

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

}

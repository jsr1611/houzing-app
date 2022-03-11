package uz.digitalone.houzingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.digitalone.houzingapp.dto.request.HouseDetailsDto;
import uz.digitalone.houzingapp.service.HouseDetailsService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/house_details")
public class HouseDetailsController {

    private final HouseDetailsService houseDetailsService;


    @PostMapping
    public HttpEntity<?> save(@RequestBody HouseDetailsDto dto) {
        return houseDetailsService.save(dto);
    }

    @GetMapping
    public HttpEntity<?> findAll() {
        return houseDetailsService.findAll();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> findById(@PathVariable("id") Long id) {
        return houseDetailsService.findById(id);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editById(@PathVariable("id") Long id,
                         @RequestBody HouseDetailsDto dto){
        return houseDetailsService.editById(id, dto);
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteById(@PathVariable("id") Long id){
        return houseDetailsService.deleteById(id);
    }
}

package uz.digitalone.houzingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.digitalone.houzingapp.dto.HouseDetailsDto;
import uz.digitalone.houzingapp.entity.HouseDetails;
import uz.digitalone.houzingapp.response.Response;
import uz.digitalone.houzingapp.service.HouseDetailsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/house_details")
public class HouseDetailsController {
    private final HouseDetailsService houseDetailsService;


    @PostMapping
    public HttpEntity<?> save(@RequestBody HouseDetailsDto dto) {
        Response response = houseDetailsService.save(dto);
       return ResponseEntity.ok(response);
    }

    @GetMapping
    public HttpEntity<?> findAll() {
        Response response = houseDetailsService.findAll();
       return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> findById(@PathVariable("id") Long id) {
        Response response = houseDetailsService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editById(@PathVariable("id") Long id,
                         @RequestBody HouseDetailsDto dto){
        Response response = houseDetailsService.editById(id, dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteById(@PathVariable("id") Long id){
        Response response = houseDetailsService.deleteById(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }
}

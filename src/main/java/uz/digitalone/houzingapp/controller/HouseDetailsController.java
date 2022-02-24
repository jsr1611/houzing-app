package uz.digitalone.houzingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public Response save(@RequestBody HouseDetailsDto dto) {
        HouseDetails houseDetails = houseDetailsService.save(dto);
        if (houseDetails == null) {
            return new Response(false, "Not saved", HttpStatus.NOT_FOUND);
        }
        return new Response(true, "Successfully saved", houseDetails);
    }

    @GetMapping
    public Response findAll() {
        List<HouseDetails> houseDetailsList = houseDetailsService.findAll();
        if (houseDetailsList == null) {
            return new Response(false, "There is no such list", HttpStatus.NOT_FOUND);
        }
        return new Response(true, "The lists are successful", houseDetailsList);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id) {
        HouseDetails houseDetails = houseDetailsService.findById(id);
        if (houseDetails == null) {
            return new Response(false, "There is no such id{" + id + "}", HttpStatus.NOT_FOUND);
        }
        return new Response(true, "HouseDetails on id{" + id + "}", houseDetails);
    }

    @PutMapping("/{id}")
    public Response editById(@PathVariable("id") Long id,
                         @RequestBody HouseDetailsDto dto){
        HouseDetails houseDetails = houseDetailsService.editById(id, dto);
        if(houseDetails == null){
            return new Response(false, "Not updated", HttpStatus.NOT_FOUND);
        }
        return new Response(true,"Successfully edited", houseDetails);
    }

    @DeleteMapping("{id}")
    public Response deleteById(@PathVariable("id") Long id){
        HouseDetails houseDetails = houseDetailsService.deleteById(id);
        if(houseDetails == null){
            return new Response(false, "Not deleted", HttpStatus.NOT_FOUND);
        }
        return new Response(true, "Successfully deleted", houseDetails);
    }
}

package uz.digitalone.houzingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.digitalone.houzingapp.dto.HouseDetailsDto;
import uz.digitalone.houzingapp.dto.LocationDto;
import uz.digitalone.houzingapp.entity.HouseDetails;
import uz.digitalone.houzingapp.entity.Location;
import uz.digitalone.houzingapp.response.Response;
import uz.digitalone.houzingapp.service.LocationService;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/location")
public class LocationController {
    private final LocationService locationService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Response save(@RequestBody LocationDto dto) {
        return new Response(true, "Successfully saved", locationService.save(dto));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Response findAll() {
        return new Response(true, "Locations ",
                Collections.singletonList(locationService.findAll()));
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Response findById(@PathVariable("id") Long id) {
        return new Response(true, "Location on id{" + id + "}", locationService.findById(id));
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public Response editById(@PathVariable("id") Long id,
                             @RequestBody LocationDto dto){
        return new Response(true,"Successfully edited", locationService.edit(dto, id));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Response deleteById(@PathVariable("id") Long id){
        return new Response(true, "Successfully deleted", locationService.delete(id));
    }

}

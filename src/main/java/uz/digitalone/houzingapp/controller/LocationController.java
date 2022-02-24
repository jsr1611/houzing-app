package uz.digitalone.houzingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.digitalone.houzingapp.dto.LocationDto;
import uz.digitalone.houzingapp.service.LocationService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/location")
public class LocationController {
    private final LocationService locationService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public HttpEntity<?> save(@RequestBody LocationDto dto) {
        return locationService.save(dto);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public HttpEntity<?> findAll() {
        return locationService.findAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public HttpEntity<?> findById(@PathVariable("id") Long id) {
        return locationService.findById(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public HttpEntity<?> editById(@PathVariable("id") Long id,
                             @RequestBody LocationDto dto){
        return locationService.edit(dto, id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public HttpEntity<?> deleteById(@PathVariable("id") Long id){
        return locationService.delete(id);
    }

}

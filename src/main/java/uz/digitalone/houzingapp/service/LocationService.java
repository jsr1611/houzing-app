package uz.digitalone.houzingapp.service;

import uz.digitalone.houzingapp.dto.LocationDto;
import uz.digitalone.houzingapp.entity.Location;

import java.util.List;

public interface LocationService {

    Location save(LocationDto dto);

    Location edit(LocationDto dto, Long id);

    Boolean delete(Long id);

    Location findById(Long id);

    List<Location> findAll();

}

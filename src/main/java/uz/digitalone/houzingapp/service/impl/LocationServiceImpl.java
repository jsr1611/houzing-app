package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.LocationDto;
import uz.digitalone.houzingapp.entity.Location;
import uz.digitalone.houzingapp.repository.LocationRepository;
import uz.digitalone.houzingapp.service.LocationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public Location optionalLocation(Long id){
        Optional<Location> byId = locationRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public Location save(LocationDto dto) {
        if (dto.getLongitude() == null || dto.getLatitude() == null)
            throw new RuntimeException("Location not found");

        return locationRepository.save(new Location(dto.getLongitude(), dto.getLatitude()));
    }

    @Override
    public Location edit(LocationDto dto, Long id) {
        Location location = optionalLocation(id);
        if (location == null){
            throw new RuntimeException("Location not found");
        }
        if (!location.getLatitude().equals(dto.getLatitude()))
            location.setLatitude(dto.getLatitude());

        if (!location.getLongitude().equals(dto.getLongitude()))
            location.setLongitude(dto.getLongitude());

        return locationRepository.save(location);
    }

    @Override
    public Boolean delete(Long id) {
        Location location = optionalLocation(id);
        if (location == null)
            throw new RuntimeException("Location not found");

        locationRepository.delete(location);
        return true;
    }

    @Override
    public Location findById(Long id) {
        Location location = optionalLocation(id);
        if (location == null)
            throw new RuntimeException("Location not found");

        return location;
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}


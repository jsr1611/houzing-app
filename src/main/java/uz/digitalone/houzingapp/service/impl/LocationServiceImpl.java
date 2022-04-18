package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.digitalone.houzingapp.dto.request.LocationDto;
import uz.digitalone.houzingapp.entity.Location;
import uz.digitalone.houzingapp.repository.LocationRepository;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.service.LocationService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public Location optionalLocation(Long id){
        Optional<Location> byId = locationRepository.findById(id);
        Location location = null;
        if (byId.isPresent())
            location = byId.get();
        return location;
    }

    @Override
    public HttpEntity<?> save(LocationDto dto) {
        Location location = create(dto);

        if (location == null) {
             Response response = new Response (false, "Location was not provided.");
             return ResponseEntity.ok(response);
        }
        Response response = new Response(true, "Successfully created.", location);
        return ResponseEntity.ok(response);
    }

    @Override
    public HttpEntity<?> edit(LocationDto dto, Long id) {
        Response response = null;
        Location location = updateById(id, dto);
        if (location == null){
            response = new Response(false,"Location not found with id " + id);
        }
        else {
            response = new Response(true, "Location was successfully saved", location);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public Location updateById(Long id, LocationDto dto) {
        Location location = findById(id);
        if(location != null && dto != null) {
            if (!location.getLatitude().equals(dto.getLatitude()))
                location.setLatitude(dto.getLatitude());

            if (!location.getLongitude().equals(dto.getLongitude()))
                location.setLongitude(dto.getLongitude());
        return locationRepository.save(location);
        }
        else
            return null;
    }

    @Override
    public HttpEntity<?> delete(Long id) {
        Location location = optionalLocation(id);
        Response response = null;
        if (location == null) {
            response = new Response(false, "Location not found with id " + id);
        }
        else {
            locationRepository.delete(location);
            response = new Response(true, "Location deleted successfully.");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> findOneById(Long id) {
        Response response = null;
        Location location = optionalLocation(id);
        if (location == null){
            response = new Response(false, "Location found with id " + id);
        } else {
            response = new Response(true, "Location information", location);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> findAll() {
        return ResponseEntity.ok(locationRepository.findAll());
    }

    @Override
    public Location create(LocationDto dto) {
        if (dto.getLongitude() != null || dto.getLatitude() != null) {
            return locationRepository.save(new Location(dto.getLongitude(), dto.getLatitude()));
        }
        return null;
    }

    @Override
    public Location findOne(LocationDto locationDto) {
        return locationRepository.findFirstByLatitudeAndLongitude(locationDto.getLatitude(), locationDto.getLongitude());
    }
    public Location findById(Long id){
        Optional<Location> optionalLocation = locationRepository.findById(id);
        return optionalLocation.orElse(null);
    }
}


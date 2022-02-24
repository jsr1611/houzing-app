package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.request.LocationDto;
import uz.digitalone.houzingapp.entity.Location;
import uz.digitalone.houzingapp.repository.LocationRepository;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.service.LocationService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
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
        if (dto.getLongitude() == null || dto.getLatitude() == null) {
             Response response = new Response (false, "Not saved Location");
             return ResponseEntity.ok(response);
        }
        Location save = locationRepository.save(new Location(dto.getLongitude(), dto.getLatitude()));
        Response response = new Response(true, "Successfully created.", save);
        return ResponseEntity.ok(response);
    }

    @Override
    public HttpEntity<?> edit(LocationDto dto, Long id) {
        Location location = optionalLocation(id);
        Response response = new Response();
        if (location == null){
            response.setSuccess(false);
            response.setMessage("Location not found with id " + id);
        }
        else {
            if (!location.getLatitude().equals(dto.getLatitude()))
                location.setLatitude(dto.getLatitude());

            if (!location.getLongitude().equals(dto.getLongitude()))
                location.setLongitude(dto.getLongitude());

            Location save = locationRepository.save(location);
            response.setMessage("Location was successfully saved");
            response.setSuccess(true);
            response.setData(save);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> delete(Long id) {
        Location location = optionalLocation(id);
        Response response = new Response();
        if (location == null) {
            response.setMessage("Location not found with id " + id);
            response.setSuccess(false);
        }
        else {
            locationRepository.delete(location);
            response.setMessage("Location deleted successfully.");
            response.setSuccess(true);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> findById(Long id) {
        Response response = new Response();
        Location location = optionalLocation(id);
        if (location == null){
            response.setMessage("Location found with id " + id);
            response.setSuccess(false);
        } else {
            response.setMessage("Location information ");
            response.setSuccess(true);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> findAll() {
        return ResponseEntity.ok(locationRepository.findAll());
    }
}


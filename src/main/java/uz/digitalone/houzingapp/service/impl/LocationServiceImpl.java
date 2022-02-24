package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.LocationDto;
import uz.digitalone.houzingapp.entity.Location;
import uz.digitalone.houzingapp.repository.LocationRepository;
import uz.digitalone.houzingapp.response.Response;
import uz.digitalone.houzingapp.service.LocationService;

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
    public HttpEntity<?> save(LocationDto dto) {
        if (dto.getLongitude() == null || dto.getLatitude() == null)
            throw new RuntimeException("Location not found");

        Location save = locationRepository.save(new Location(dto.getLongitude(), dto.getLatitude()));
        return ResponseEntity.ok(save);
    }

    @Override
    public HttpEntity<?> edit(LocationDto dto, Long id) {
        Location location = optionalLocation(id);
        if (location == null){
            Response response = new Response(false, "Location Not saved", null);
            return ResponseEntity.ok(response);
        }
        if (!location.getLatitude().equals(dto.getLatitude()))
            location.setLatitude(dto.getLatitude());

        if (!location.getLongitude().equals(dto.getLongitude()))
            location.setLongitude(dto.getLongitude());

        Location save = locationRepository.save(location);
        Response response = new Response(true, "Succesffuly save", save);

        return ResponseEntity.ok(response);
    }

    @Override
    public HttpEntity<?> delete(Long id) {
        Location location = optionalLocation(id);
        if (location == null)
            throw new RuntimeException("Location not found");

        locationRepository.delete(location);
        return ResponseEntity.ok("Successfully delete !!!");
    }

    @Override
    public HttpEntity<?> findById(Long id) {
        Location location = optionalLocation(id);
        if (location == null)
            throw new RuntimeException("Location not found");

        return ResponseEntity.ok(location);
    }

    @Override
    public HttpEntity<?> findAll() {
        return ResponseEntity.ok(locationRepository.findAll());
    }
}


package uz.digitalone.houzingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.digitalone.houzingapp.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findFirstByLatitudeAndLongitude(Double latitude, Double longitude);
}

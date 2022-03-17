package uz.digitalone.houzingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.digitalone.houzingapp.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}

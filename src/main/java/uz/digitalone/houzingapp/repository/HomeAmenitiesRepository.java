package uz.digitalone.houzingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.digitalone.houzingapp.entity.HomeAmenities;

@Repository
public interface HomeAmenitiesRepository extends JpaRepository<HomeAmenities, Long> {
}

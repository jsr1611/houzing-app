package uz.digitalone.houzingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.digitalone.houzingapp.entity.HouseDetails;

public interface HouseDetailsRepository extends JpaRepository<HouseDetails, Long> {
}

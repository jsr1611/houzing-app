package uz.digitalone.houzingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.digitalone.houzingapp.entity.HouseDetails;

@Repository
public interface HouseDetailsRepository extends JpaRepository<HouseDetails, Long> {
}

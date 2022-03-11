package uz.digitalone.houzingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uz.digitalone.houzingapp.entity.House;

public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House> {
}

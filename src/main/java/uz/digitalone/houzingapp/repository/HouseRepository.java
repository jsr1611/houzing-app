package uz.digitalone.houzingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uz.digitalone.houzingapp.entity.House;
import uz.digitalone.houzingapp.entity.User;

import java.util.List;
import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House> {
public interface HouseRepository extends JpaRepository<House, Long> {

    Optional<List<House>> findByUser(User user);

}

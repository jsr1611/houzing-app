package uz.digitalone.houzingapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uz.digitalone.houzingapp.entity.House;
import uz.digitalone.houzingapp.entity.User;


public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House> {

    Page<House> findAllByUser(User user, Pageable pageable);
}

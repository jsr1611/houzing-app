package uz.digitalone.houzingapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.digitalone.houzingapp.entity.House;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House> {

    Page<House> findAllByCategoryId(Long category_id, Pageable pageable);

    @Query(value = "select * from houses where favorite = true ", nativeQuery = true )
    List<House> findAllByFavourite();
}

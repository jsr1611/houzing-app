package uz.digitalone.houzingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.digitalone.houzingapp.entity.HouseComponents;

@Repository
public interface HouseComponentsRepository extends JpaRepository<HouseComponents, Long> {
}

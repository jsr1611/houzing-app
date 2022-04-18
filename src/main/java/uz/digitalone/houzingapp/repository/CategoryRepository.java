package uz.digitalone.houzingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.digitalone.houzingapp.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

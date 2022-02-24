package uz.digitalone.houzingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.digitalone.houzingapp.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

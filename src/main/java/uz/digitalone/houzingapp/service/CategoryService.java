package uz.digitalone.houzingapp.service;

import org.springframework.http.HttpEntity;
import uz.digitalone.houzingapp.dto.request.CategoryDto;
import uz.digitalone.houzingapp.entity.Category;

import java.awt.print.Pageable;

public interface CategoryService {

    HttpEntity<?> saveCategory(CategoryDto categoryDto);

    HttpEntity<?> getOne(Long id);

    HttpEntity<?> getAll();

    HttpEntity<?> updateById(CategoryDto categoryDto, Long id);

    HttpEntity<?> deleteCategory(Long id);

    HttpEntity<?> findAllPageable(Pageable pageable);

    Category findById(Long category_id);
}

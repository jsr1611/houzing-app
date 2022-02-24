package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.CategoryDto;
import uz.digitalone.houzingapp.repository.CategoryRepository;
import uz.digitalone.houzingapp.service.CategoryService;

import java.awt.print.Pageable;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public HttpEntity<?> saveCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public HttpEntity<?> getOne(Long id) {
        return null;
    }

    @Override
    public HttpEntity<?> getAll() {
        return null;
    }

    @Override
    public HttpEntity<?> updateById(CategoryDto categoryDto, Long id) {
        return null;
    }

    @Override
    public HttpEntity<?> deleteCategory(Long id) {
        return null;
    }

    @Override
    public HttpEntity<?> findAllPageable(Pageable pageable) {
        return null;
    }

}

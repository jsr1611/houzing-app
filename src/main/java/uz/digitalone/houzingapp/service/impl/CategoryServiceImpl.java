package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.digitalone.houzingapp.dto.request.CategoryDto;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.entity.Category;
import uz.digitalone.houzingapp.repository.CategoryRepository;
import uz.digitalone.houzingapp.service.CategoryService;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public HttpEntity<?> saveCategory(CategoryDto categoryDto) {
        Category category = new Category();

        category.setName(categoryDto.getName());
        if (categoryDto.getParentId() != null) {
            Category parent = findById(categoryDto.getParentId());
            if (parent != null){
                category.setParent(parent);
            }
        }
        categoryRepository.save(category);
        Response response = new Response(true, "Successfully created.", category);
        return ResponseEntity.ok(response);
    }

    @Override
    public HttpEntity<?> getOne(Long id) {
        Category category = findById(id);
        Response response = null;
        if(category == null){
            response = new Response(false, "Category was not found with id {" + id + "}");
        }else {
            response = new Response(true, "Category", category);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> getAll() {
        Response response = null;
        List<Category> categories = categoryRepository.findAll();
        List<String> result = categories
                .stream()
                .sorted(Comparator.comparing(Category::getName))
                .map(category -> category.getName().toUpperCase())
                .collect(Collectors.toList());
        if (categories.size() == 0){
            response = new Response(true, "Categories Not Found");
        }
        else {
            response = new Response(true, "Category List", new ArrayList<>(result));
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> updateById(CategoryDto categoryDto, Long id) {
        Category category = findById(id);
        Response response = null;
        if (category == null){
            response = new Response(false,"Category Not Found With Id [" + id + " ]");
        }
        else {
            category.setName((categoryDto.getName()));
            Category parent = findById(categoryDto.getParentId());
            if (parent != null){
                category.setParent(parent);
            }
            category = categoryRepository.save(category);
            response = new Response(true, "Updated Category", category);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> deleteCategory(Long id) {
        Response response = null;
        Category category = findById(id);

        if (category == null) {
            response = new Response(false, "Category Not Found with id: [" + id + "]");
        }
        else {
            categoryRepository.delete(category);
            response = new Response(true, "Category {" + category.getName()+"} was successfully deleted");
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> findAllPageable(Pageable pageable) {
        Response response = null;
        Page<Category> categoryAll = categoryRepository.findAll(pageable);
        List<Category> categoryAllContent = categoryAll.getContent();
        List<String> result = categoryAllContent
                .stream()
                .sorted(Comparator.comparing(Category::getName))
                .map(category -> category.getName().toUpperCase())
                .collect(Collectors.toList());


        if(categoryAllContent.size() == 0){
            response = new Response(true, "No categories were found");
        }
        else {
            response = new Response(true, "Category list", result);
        }
//        response.setDataList(new ArrayList<>(categoryAllContent));
        response.getMap().put("size", categoryAll.getSize());
        response.getMap().put("total_elements", categoryAll.getTotalElements());
        response.getMap().put("total_pages", categoryAll.getTotalPages());
        return ResponseEntity.ok(response);
    }


    @Override
    public Category findById(Long category_id) {
        Optional<Category> optionalCategory = categoryRepository.findById(category_id);
        return optionalCategory.orElse(null);
    }

}

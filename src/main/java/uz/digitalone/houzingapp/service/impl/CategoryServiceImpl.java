package uz.digitalone.houzingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.digitalone.houzingapp.dto.request.CategoryDto;
import uz.digitalone.houzingapp.dto.response.Response;
import uz.digitalone.houzingapp.entity.Category;
import uz.digitalone.houzingapp.repository.CategoryRepository;
import uz.digitalone.houzingapp.service.CategoryService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
        Response response = new Response();
        if(category == null){
            response.setSuccess(false);
            response.setMessage("Category was not found with id {" + id + "}");
        }else {
            response.setSuccess(true);
            response.setData(category);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> getAll() {
        Response response = new Response();
        List<Category> categories = categoryRepository.findAll();
        response.setSuccess(true);
        if (categories.size() == 0){
            response.setMessage("Categories Not Found");
        }
        else {
            response.setDataList(new ArrayList<>(categories));
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public HttpEntity<?> updateById(CategoryDto categoryDto, Long id) {
        Category category = new Category();
        Response response = new Response();
        if (category == null){
            response.setSuccess(false);
            response.setMessage("Category Not Found With Id [" + id + " ]");
        }
        else {
            category.setName((categoryDto.getName()));
            Category parent = findById(categoryDto.getParentId());
            if (parent != null){
                category.setParent(parent);
            }
            category = categoryRepository.save(category);
            response.setData(category);
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Override
    public HttpEntity<?> deleteCategory(Long id) {
        Response response = new Response();
        Category category = new Category();

        if (category == null) {
            response.setSuccess(false);
            response.setMessage("Category Not Found with id: [" + id + "]");
        }
        else {
            categoryRepository.delete(category);
            response.setSuccess(true);
            response.setMessage("Category {" + category.getName()+"} was successfully deleted");
        }
        return null;
    }

    @Override
    public HttpEntity<?> findAllPageable(Pageable pageable) {
        Response response = new Response();
        Page<Category> categoryAll = categoryRepository.findAll(pageable);
        List<Category> categoryAllContent = categoryAll.getContent();

        response.setSuccess(true);
        if(categoryAllContent.size() == 0){
            response.setMessage("No categories were found");
        }
        else {
            response.setMessage("Category list");
        }
        response.setDataList(new ArrayList<>(categoryAllContent));
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

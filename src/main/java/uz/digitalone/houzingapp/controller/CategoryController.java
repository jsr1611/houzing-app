package uz.digitalone.houzingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import uz.digitalone.houzingapp.dto.CategoryDto;
import uz.digitalone.houzingapp.service.CategoryService;
import uz.digitalone.houzingapp.utils.ApiPageable;

import java.awt.print.Pageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public HttpEntity<?> saveCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.saveCategory(categoryDto);
    }

    @GetMapping("/getOne/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        return categoryService.getOne(id);
    }

    @GetMapping
    public HttpEntity<?> getAll(){
        return categoryService.getAll();
    }

    @PutMapping("/updateCategory/{id}")
    public HttpEntity<?> updateById(@RequestBody CategoryDto categoryDto, @PathVariable Long id){
        return categoryService.updateById(categoryDto, id);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }

    @ApiPageable
    @GetMapping
    public HttpEntity<?> getAllAsPageable(@ApiIgnore Pageable pageable){
        return categoryService.findAllPageable(pageable);
    }
}

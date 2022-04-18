package uz.digitalone.houzingapp.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import uz.digitalone.houzingapp.dto.request.CategoryDto;
import uz.digitalone.houzingapp.service.CategoryService;
import uz.digitalone.houzingapp.utils.ApiPageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @ApiOperation("Ushbu API yangi kategoriya qo`shish uchun ishlatiladi")
    @PostMapping
    public HttpEntity<?> saveCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.saveCategory(categoryDto);
    }

    @ApiOperation("Ushbu API Id bo'yicha kategoriyani topish uchun ishlatiladi")
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        return categoryService.getOne(id);
    }

    @ApiOperation("Ushbu API kategoriya listini ko'rish uchun ishlatiladi")
    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        return categoryService.getAll();
    }

    @ApiOperation("Ushbu API Id bo'yicha mavjud kategoriyani edit qilish uchun ishlatiladi")
    @PutMapping("/{id}")
    public HttpEntity<?> updateById(@RequestBody CategoryDto categoryDto, @PathVariable Long id){
        return categoryService.updateById(categoryDto, id);
    }

    @ApiOperation("Ushbu API Id bo'yicha kategoriyani o'chirish uchun ishlatiladi")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }

    @ApiOperation("Ushbu API kategoriya listini pageable qilib ko'rish uchun ishlatiladi")
    @ApiPageable
    @GetMapping
    public HttpEntity<?> getAllAsPageable(@ApiIgnore Pageable pageable){
        return categoryService.findAllPageable(pageable);
    }
}

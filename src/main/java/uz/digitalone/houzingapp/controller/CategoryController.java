package uz.digitalone.houzingapp.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import uz.digitalone.houzingapp.dto.request.CategoryDto;
import uz.digitalone.houzingapp.service.CategoryService;
import uz.digitalone.houzingapp.service.impl.MyUserService;
import uz.digitalone.houzingapp.utils.ApiPageable;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final MyUserService userService;

    @ApiOperation("Ushbu API yangi kategoriya qo`shish uchun ishlatiladi")
    @PostMapping
    public HttpEntity<?> saveCategory(@RequestBody CategoryDto categoryDto){
        log.info(userService.getUserIdInfo() +  "POST /categories: " + categoryDto);
        return categoryService.saveCategory(categoryDto);
    }

    @ApiOperation("Ushbu API Id bo'yicha kategoriyani topish uchun ishlatiladi")
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        log.info(userService.getUserIdInfo() +  "GET /categories/"+ id);
        return categoryService.getOne(id);
    }

    @ApiOperation("Ushbu API kategoriya listini ko'rish uchun ishlatiladi")
    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        log.info(userService.getUserIdInfo() +  "GET /categories/list");
        return categoryService.getAll();
    }

    @ApiOperation("Ushbu API Id bo'yicha mavjud kategoriyani edit qilish uchun ishlatiladi")
    @PutMapping("/{id}")
    public HttpEntity<?> updateById(@RequestBody CategoryDto categoryDto, @PathVariable Long id){
        log.info(userService.getUserIdInfo() +  "PUT /categories/"+ id + " dto: "+ categoryDto);
        return categoryService.updateById(categoryDto, id);
    }

    @ApiOperation("Ushbu API Id bo'yicha kategoriyani o'chirish uchun ishlatiladi")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable Long id){
        log.info(userService.getUserIdInfo() +  "DELETE /categories/"+id);
        return categoryService.deleteCategory(id);
    }

    @ApiOperation("Ushbu API kategoriya listini pageable qilib ko'rish uchun ishlatiladi")
    @ApiPageable
    @GetMapping
    public HttpEntity<?> getAllAsPageable(@ApiIgnore Pageable pageable){
        log.info(userService.getUserIdInfo() +  "GET /categories pageable: " + pageable);
        return categoryService.findAllPageable(pageable);
    }
}

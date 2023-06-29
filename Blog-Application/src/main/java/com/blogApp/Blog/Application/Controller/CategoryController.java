package com.blogApp.Blog.Application.Controller;

import com.blogApp.Blog.Application.Dto.CategoryRequest;
import com.blogApp.Blog.Application.Dto.CategoryResponse;
import com.blogApp.Blog.Application.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        categoryService.createCategory(categoryRequest);
        return "Category is Created";
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getAllCategories(){
        return categoryService.findAllCategory();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse getCategoryById(@PathVariable("id") Integer id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String updateCategory(@Valid @RequestBody CategoryRequest categoryRequest,@PathVariable Integer id){
        categoryService.updateUser(categoryRequest,id);
        return "User is updated Successfully";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteById(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return "User has been deleted successfully";
    }

}

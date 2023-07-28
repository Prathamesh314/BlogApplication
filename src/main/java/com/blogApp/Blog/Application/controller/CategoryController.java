package com.blogApp.Blog.Application.controller;

import com.blogApp.Blog.Application.dto.CategoryRequest;
import com.blogApp.Blog.Application.dto.CategoryResponse;
import com.blogApp.Blog.Application.service.CategoryService;
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
    public String createCategory(@RequestBody CategoryRequest categoryRequest){
        categoryService.createCategory(categoryRequest);
        return "Category Added Successfully";
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getAllCats(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse getCatById(@PathVariable Long id){
        return categoryService.getCatById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateCategory(@RequestBody CategoryRequest categoryRequest,@PathVariable Long id){
        categoryService.updateCategory(categoryRequest,id);
        return "Category Updated Successfully";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCatById(@PathVariable Long id){
        categoryService.deleteCat(id);
        return "Category Deleted Successfully";
    }

}

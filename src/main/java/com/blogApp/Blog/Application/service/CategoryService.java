package com.blogApp.Blog.Application.service;

import com.blogApp.Blog.Application.dto.CategoryRequest;
import com.blogApp.Blog.Application.dto.CategoryResponse;
import com.blogApp.Blog.Application.exception.UserDefinedException;
import com.blogApp.Blog.Application.model.Category;
import com.blogApp.Blog.Application.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void createCategory(CategoryRequest categoryRequest){
        Category category = Category.builder()
                .title(categoryRequest.getTitle())
                .description(categoryRequest.getDescription())
                .build();
        categoryRepository.save(category);
    }

    public List<CategoryResponse> getAllCategories(){
        return categoryRepository.findAll().stream().map(this::MapToResponse).toList();
    }

    public CategoryResponse getCatById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(()->new UserDefinedException("Category","Id",id));
        return MapToResponse(category);
    }

    public void updateCategory(CategoryRequest categoryRequest,Long id){
        Category category = categoryRepository.findById(id).orElseThrow(()->new UserDefinedException("Category","Id",id));
        category.setTitle(categoryRequest.getTitle());
        category.setDescription(categoryRequest.getDescription());
        categoryRepository.save(category);
    }

    public void deleteCat(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(()->new UserDefinedException("Category","Id",id));
        categoryRepository.delete(category);
    }

    private CategoryResponse MapToResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }



}

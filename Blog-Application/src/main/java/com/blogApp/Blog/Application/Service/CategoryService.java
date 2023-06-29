package com.blogApp.Blog.Application.Service;

import com.blogApp.Blog.Application.Dto.CategoryRequest;
import com.blogApp.Blog.Application.Dto.CategoryResponse;
import com.blogApp.Blog.Application.Exceptions.ResourceNotFoundException;
import com.blogApp.Blog.Application.Model.Category;
import com.blogApp.Blog.Application.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void createCategory(CategoryRequest categoryRequest){
        Category category = Category.builder()
                .categoryTitle(categoryRequest.getCategoryTitle())
                .categoryDescription(categoryRequest.getCategoryDescription())
                .build();
        categoryRepository.save(category);
    }

    public List<CategoryResponse> findAllCategory(){
        return categoryRepository.findAll().stream().map(this::MapToCategoryResponse).toList();
    }

    private CategoryResponse MapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .categoryTitle(category.getCategoryTitle())
                .id(category.getId())
                .categoryDescription(category.getCategoryDescription())
                .build();
    }

    public CategoryResponse getCategoryById(int id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id",id));
        return MapToCategoryResponse(category);
    }

    public CategoryResponse updateUser(CategoryRequest categoryRequest,int id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id",id));
        category.setId(category.getId());
        category.setCategoryTitle(categoryRequest.getCategoryTitle());
        category.setCategoryDescription(categoryRequest.getCategoryDescription());
        categoryRepository.save(category);
        return MapToCategoryResponse(category);
    }

    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }
}

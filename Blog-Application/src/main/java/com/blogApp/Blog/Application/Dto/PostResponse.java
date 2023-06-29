package com.blogApp.Blog.Application.Dto;

import com.blogApp.Blog.Application.Model.Category;
import com.blogApp.Blog.Application.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private int id;
    private String title;
    private String image;
    private String content;
    private Date addedDate;
    private CategoryResponse category;
    private UserResponse user;
}

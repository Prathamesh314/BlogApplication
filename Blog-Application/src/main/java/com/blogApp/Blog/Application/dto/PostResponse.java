package com.blogApp.Blog.Application.dto;

import com.blogApp.Blog.Application.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String image;
    private UserResponse user;
    private CategoryResponse category;
    private Set<Comment> comments = new HashSet<>();
}

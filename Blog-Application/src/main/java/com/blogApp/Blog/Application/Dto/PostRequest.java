package com.blogApp.Blog.Application.Dto;

import com.blogApp.Blog.Application.Model.Category;
import com.blogApp.Blog.Application.Model.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String content;
    private String imageName;
}

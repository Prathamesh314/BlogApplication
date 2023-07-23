package com.blogApp.Blog.Application.dto;

import com.blogApp.Blog.Application.model.Post;
import com.blogApp.Blog.Application.model.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String comment;
    private PostResponse post;
    private UserResponse user;
}

package com.blogApp.Blog.Application.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private int id;
    private String content;
    private Set<UserResponse> user;
    private Set<PostResponse> post;
}

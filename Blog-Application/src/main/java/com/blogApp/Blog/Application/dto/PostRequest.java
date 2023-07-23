package com.blogApp.Blog.Application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long id;
    private String title;
    private String content;
    private String image;
}

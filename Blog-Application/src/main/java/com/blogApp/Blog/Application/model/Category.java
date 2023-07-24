package com.blogApp.Blog.Application.model;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_category")
public class Category {

    private Long id;
    private String title;
    @ManyToOne
    private List<Post> posts;

}

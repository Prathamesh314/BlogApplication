package com.blogApp.Blog.Application.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private String image;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;
    @OneToMany
    private List<Comment> comments;

}

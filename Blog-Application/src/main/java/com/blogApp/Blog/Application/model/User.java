package com.blogApp.Blog.Application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String about;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> posts;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Comment> comments;

}

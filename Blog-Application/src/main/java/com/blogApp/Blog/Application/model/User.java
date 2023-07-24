package com.blogApp.Blog.Application.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    @Size(min = 3,max = 12,message = "Username must be of length 3-12 characters")
    private String username;
    @Email(message = "Email is not valid")
    private String email;
    @NotNull
    @Size(min = 8,max = 12,message = "Password should be of length 8-12 characters")
    private String password;
    @NotNull
    private String about;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> posts;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Comment> comments;

}

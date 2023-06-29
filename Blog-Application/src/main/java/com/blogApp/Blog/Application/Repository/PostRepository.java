package com.blogApp.Blog.Application.Repository;

import com.blogApp.Blog.Application.Model.Category;
import com.blogApp.Blog.Application.Model.Post;
import com.blogApp.Blog.Application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}

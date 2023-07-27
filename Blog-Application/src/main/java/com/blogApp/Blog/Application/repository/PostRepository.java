package com.blogApp.Blog.Application.repository;

import com.blogApp.Blog.Application.model.Category;
import com.blogApp.Blog.Application.model.Post;
import com.blogApp.Blog.Application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String keyword);
}

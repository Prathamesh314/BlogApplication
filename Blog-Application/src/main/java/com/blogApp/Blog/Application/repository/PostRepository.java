package com.blogApp.Blog.Application.repository;

import com.blogApp.Blog.Application.model.Category;
import com.blogApp.Blog.Application.model.Post;
import com.blogApp.Blog.Application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findByUser(User user);
    Optional<Post> findByCategory(Category category);
}

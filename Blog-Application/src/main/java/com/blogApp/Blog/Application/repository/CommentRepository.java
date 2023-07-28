package com.blogApp.Blog.Application.repository;

import com.blogApp.Blog.Application.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Long id);
    List<Comment> findByUserId(Long id);
}

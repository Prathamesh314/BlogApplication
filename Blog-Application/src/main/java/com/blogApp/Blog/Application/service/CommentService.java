package com.blogApp.Blog.Application.service;

import com.blogApp.Blog.Application.dto.*;
import com.blogApp.Blog.Application.exception.UserDefinedException;
import com.blogApp.Blog.Application.model.Category;
import com.blogApp.Blog.Application.model.Comment;
import com.blogApp.Blog.Application.model.Post;
import com.blogApp.Blog.Application.model.User;
import com.blogApp.Blog.Application.repository.CommentRepository;
import com.blogApp.Blog.Application.repository.PostRepository;
import com.blogApp.Blog.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // create
    public void createComment(CommentRequest commentRequest,Long userId,Long postId){
        User user = userRepository.findById(userId).orElseThrow(()->new UserDefinedException("User","ID",userId));
        Post post = postRepository.findById(postId).orElseThrow(()->new UserDefinedException("Post","ID",postId));
        Comment comment = new Comment();
        comment.setComment(commentRequest.getComment());
        comment.setPost(post);
        comment.setUser(user);
        commentRepository.save(comment);
    }

    public void deleteComment(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(()->new UserDefinedException("Comment","ID",id));
        commentRepository.delete(comment);
    }

}

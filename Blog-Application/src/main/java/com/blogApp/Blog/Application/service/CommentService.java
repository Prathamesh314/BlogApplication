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
        Comment comment = Comment.builder()
                .comment(commentRequest.getComment())
                .user(user)
                .post(post)
                .build();
        commentRepository.save(comment);
    }

    public List<CommentResponse> getAllComments(){
        return commentRepository.findAll().stream().map(this::MapToResponse).toList();
    }

    private CommentResponse MapToResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .user(MapToUserResponse(comment.getUser()))
                .post(MapToPostResponse(comment.getPost()))
                .build();
    }

    private PostResponse MapToPostResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .image(post.getImage())
                .content(post.getContent())
                .user(MapToUserResponse(post.getUser()))
                .category(MapToCategoryResponse(post.getCategory()))
                .build();
    }

    private CategoryResponse MapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }

    private UserResponse MapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .about(user.getAbout())
                .build();
    }

}

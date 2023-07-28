package com.blogApp.Blog.Application.service;

import com.blogApp.Blog.Application.dto.CommentResponse;
import com.blogApp.Blog.Application.dto.UserRequest;
import com.blogApp.Blog.Application.dto.UserResponse;
import com.blogApp.Blog.Application.exception.UserDefinedException;
import com.blogApp.Blog.Application.model.Comment;
import com.blogApp.Blog.Application.model.User;
import com.blogApp.Blog.Application.repository.CommentRepository;
import com.blogApp.Blog.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    public void createUser(UserRequest userRequest)
    {
        User user = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .about(userRequest.getAbout())
                .build();
        userRepository.save(user);
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(this::MapToResponse).toList();
    }

    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new UserDefinedException("User","id",id));
        return MapToResponse(user);
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new UserDefinedException("User","ID",id));
        userRepository.deleteById(id);
    }

    public void updateUser(UserRequest userRequest,Long id){
        User user = userRepository.findById(id).orElseThrow(()->new UserDefinedException("User","ID",id));
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setAbout(userRequest.getAbout());
        userRepository.save(user);
    }

    private UserResponse MapToResponse(User user) {
        List<Comment> comments = commentRepository.findByUserId(user.getId());
        List<CommentResponse> commentResponses = comments.stream().map(this::MapToCommentResponse).toList();
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .about(user.getAbout())
                .comments(commentResponses)
                .build();
    }

    private CommentResponse MapToCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .build();
    }

}

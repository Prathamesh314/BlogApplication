package com.blogApp.Blog.Application.service;

import com.blogApp.Blog.Application.dto.UserRequest;
import com.blogApp.Blog.Application.dto.UserResponse;
import com.blogApp.Blog.Application.exception.UserDefinedException;
import com.blogApp.Blog.Application.model.User;
import com.blogApp.Blog.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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

    private UserResponse MapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .about(user.getAbout())
                .build();
    }

}

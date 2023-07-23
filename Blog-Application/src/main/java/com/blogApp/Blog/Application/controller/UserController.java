package com.blogApp.Blog.Application.controller;

import com.blogApp.Blog.Application.dto.UserRequest;
import com.blogApp.Blog.Application.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    // create
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody UserRequest userRequest){
        return "User created successfully";
    }

    // get
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(){
        return null;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserByID(@PathVariable Long id){
        return null;
    }

    // delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable Long id){
        return "User deleted successfully";
    }

    // update
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id){
        return "User updated successfully";
    }

}

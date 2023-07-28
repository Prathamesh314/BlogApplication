package com.blogApp.Blog.Application.controller;

import com.blogApp.Blog.Application.dto.UserRequest;
import com.blogApp.Blog.Application.dto.UserResponse;
import com.blogApp.Blog.Application.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    // create
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@Valid @RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
        return "User created successfully";
    }

    // get
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserByID(@PathVariable Long id){
        return userService.getUserById(id);
    }

    // delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    // update
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUser(@Valid @RequestBody UserRequest userRequest, @PathVariable Long id){
        userService.updateUser(userRequest,id);
        return "User updated successfully";
    }

}

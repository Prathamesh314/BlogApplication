package com.blogApp.Blog.Application.Controller;

import com.blogApp.Blog.Application.Dto.UserDto;
import com.blogApp.Blog.Application.Dto.UserResponse;
import com.blogApp.Blog.Application.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@Valid @RequestBody UserDto userDto){
        userService.addUser(userDto);
        return "User is added";
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUser(@RequestBody UserDto userDto,@PathVariable  Integer id){
        userService.updateUser(userDto,id);
        return "User has been Updated";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return "User deleted Successfully";
    }

}

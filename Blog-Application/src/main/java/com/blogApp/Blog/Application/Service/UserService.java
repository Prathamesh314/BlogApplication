package com.blogApp.Blog.Application.Service;

import com.blogApp.Blog.Application.Dto.UserDto;
import com.blogApp.Blog.Application.Dto.UserResponse;
import com.blogApp.Blog.Application.Exceptions.ResourceNotFoundException;
import com.blogApp.Blog.Application.Model.User;
import com.blogApp.Blog.Application.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void addUser(UserDto userDto){
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .about(userDto.getAbout())
                .password(userDto.getPassword())
                .build();

        userRepository.save(user);

    }

    public UserResponse updateUser(UserDto userDto, Integer id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id",id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .about(user.getAbout())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        return userResponse;
    }

    public UserResponse getUserById(Integer id){
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .about(user.getAbout())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        return userResponse;
    }

    public List<UserResponse> findAll(){
        return userRepository.findAll().stream().map(user -> MapToResponse(user)).toList();
    }

    private UserResponse MapToResponse(User user) {
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .about(user.getAbout())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        return userResponse;
    }

    public void deleteUser(Integer id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","Id",id));
        userRepository.delete(user);
    }

}

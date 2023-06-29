package com.blogApp.Blog.Application.Dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotEmpty
    @Size(min = 4,message = "UserName must be of min 4 characters")
    private String name;
    @Email(message = "Invalid UserName or Email Address")
    private String email;
    @NotEmpty
    @Size(min=8,max = 12,message = "Password length must be between 8 - 12")
    private String password;
    @NotEmpty
    private String about;
}

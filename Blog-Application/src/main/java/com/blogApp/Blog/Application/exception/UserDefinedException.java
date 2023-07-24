package com.blogApp.Blog.Application.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDefinedException extends RuntimeException{
    String username;
    String id;
    Long userid;

    public UserDefinedException(String username, String id, Long userid) {
        super(String.format("%s not found with %s: %s",username,id,userid));
        this.username = username;
        this.id = id;
        this.userid = userid;
    }
}

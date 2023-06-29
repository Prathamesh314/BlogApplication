package com.blogApp.Blog.Application.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String user;
    String id;
    Integer id1;

    public ResourceNotFoundException(String user, String id, Integer id1) {
        super(String.format("%s not found with %s: %s",user,id,id1));
        this.user = user;
        this.id = id;
        this.id1 = id1;
    }
}

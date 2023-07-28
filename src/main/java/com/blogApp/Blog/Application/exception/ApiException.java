package com.blogApp.Blog.Application.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }

    public ApiException() {
    }
}

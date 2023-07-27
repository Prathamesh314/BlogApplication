package com.blogApp.Blog.Application.controller;

import com.blogApp.Blog.Application.dto.CommentRequest;
import com.blogApp.Blog.Application.dto.CommentResponse;
import com.blogApp.Blog.Application.dto.UserRequest;
import com.blogApp.Blog.Application.dto.UserResponse;
import com.blogApp.Blog.Application.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/user/{userID}/post/{postID}/comment")
    @ResponseStatus(HttpStatus.OK)
    public String createComment(@RequestBody CommentRequest commentRequest, @PathVariable Long userID, @PathVariable Long postID){
        commentService.createComment(commentRequest,userID,postID);
        return "Comment Created Successfully";
    }

    @GetMapping("/comment")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponse> getAllComments(){
        return commentService.getAllComments();
    }

}

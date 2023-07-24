package com.blogApp.Blog.Application.controller;

import com.blogApp.Blog.Application.dto.PostRequest;
import com.blogApp.Blog.Application.dto.PostResponse;
import com.blogApp.Blog.Application.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/user/{userID}/category/{categoryID}")
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody PostRequest postRequest,@PathVariable Long userID,@PathVariable Long categoryID){
        postService.createPost(postRequest,userID,categoryID);
        return "Post Created Successfully";
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{postID}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse getPostById(@PathVariable Long postID){
        return postService.getPostById(postID);
    }

    @PutMapping("/{postID}")
    @ResponseStatus(HttpStatus.OK)
    public String updatePost(@RequestBody PostRequest postRequest,@PathVariable Long postID){
        postService.updatePost(postRequest,postID);
        return "Post Updated Successfully";
    }

    @DeleteMapping("/{postID}")
    @ResponseStatus(HttpStatus.OK)
    public String deletePost(@PathVariable Long postD){
        postService.deletePost(postD);
        return "Post Deleted Successfully";
    }

}

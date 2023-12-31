package com.blogApp.Blog.Application.controller;

import com.blogApp.Blog.Application.dto.PaginationResponse;
import com.blogApp.Blog.Application.dto.PostRequest;
import com.blogApp.Blog.Application.dto.PostResponse;
import com.blogApp.Blog.Application.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @PostMapping("/image/upload/{postID}")
    @ResponseStatus(HttpStatus.OK)
    public String uploadImage(
            @RequestParam(value = "image")MultipartFile image,
            @PathVariable Long postID
            ) throws IOException {
        postService.uploadImage(image, postID);
        return "Image uploaded Successfully";
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public PaginationResponse getAllPosts(
            @RequestParam(value = "pageSize",defaultValue = "3",required = false) Integer pageSize,
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        return postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
    }

    @GetMapping("/user/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getPostsByUser(@PathVariable Long userID){
        return postService.getPostsByUsers(userID);
    }

    @GetMapping("/category/{categoryID}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getPostByCategory(@PathVariable Long categoryID){
        return postService.getPostByCategory(categoryID);
    }

    @GetMapping("/{postID}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse getPostById(@PathVariable Long postID){
        return postService.getPostById(postID);
    }

    @GetMapping("/search/{keywords}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> searchPosts(@PathVariable String keywords){
        return postService.searchPosts(keywords);
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

package com.blogApp.Blog.Application.Controller;

import com.blogApp.Blog.Application.Dto.PostRequest;
import com.blogApp.Blog.Application.Dto.PostResponse;
import com.blogApp.Blog.Application.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody PostRequest postRequest,@PathVariable Integer userId,@PathVariable Integer categoryId){
        postService.createPost(postRequest,userId,categoryId);
        return "Post is created successfully";
    }

    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize
    ){
        return postService.getAllPosts(pageSize,pageNumber);
    }

    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse getPostById(@PathVariable Integer id){
        return postService.findPostById(id);
    }

    @GetMapping("/user/{userId}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getPostByUser(@PathVariable int userId){
        return postService.findPostByUser(userId);
    }

    @GetMapping("/category/{categoryId}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getPostByCategory(@PathVariable int categoryId){
        return postService.findPostByCategory(categoryId);
    }

    @PutMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse updatePost(@RequestBody PostRequest postRequest,@PathVariable int postId){
        return postService.updatePost(postRequest,postId);
    }

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletePostById(@PathVariable int id){
        postService.deletePost(id);
        return "Post is deleted Successfully";
    }


}

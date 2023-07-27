package com.blogApp.Blog.Application.service;

import com.blogApp.Blog.Application.dto.CategoryResponse;
import com.blogApp.Blog.Application.dto.PostRequest;
import com.blogApp.Blog.Application.dto.PostResponse;
import com.blogApp.Blog.Application.dto.UserResponse;
import com.blogApp.Blog.Application.exception.UserDefinedException;
import com.blogApp.Blog.Application.model.Category;
import com.blogApp.Blog.Application.model.Post;
import com.blogApp.Blog.Application.model.User;
import com.blogApp.Blog.Application.repository.CategoryRepository;
import com.blogApp.Blog.Application.repository.PostRepository;
import com.blogApp.Blog.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public void createPost(PostRequest postRequest,Long user_id,Long cat_id){
        User user = userRepository.findById(user_id).orElseThrow(()->new UserDefinedException("User","ID",user_id));
        Category category = categoryRepository.findById(cat_id).orElseThrow(()->new UserDefinedException("Category","ID",cat_id));
        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .image(postRequest.getImage())
                .user(user)
                .category(category)
                .build();
        postRepository.save(post);
    }

    public List<PostResponse> getAllPosts(Integer pageNumber,Integer pageSize){

        Pageable p = PageRequest.of(pageNumber,pageSize);
        Page<Post> posts = postRepository.findAll(p);
        List<Post> allposts = posts.getContent();

        return allposts.stream().map(this::MapToResponse).toList();
    }

    public List<PostResponse> getPostsByUsers(Long userID){
        User user = userRepository.findById(userID).orElseThrow(()->new UserDefinedException("User","ID",userID));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map(this::MapToResponse).toList();

    }

    public List<PostResponse> getPostByCategory(Long catID){
        Category category = categoryRepository.findById(catID).orElseThrow(()->new UserDefinedException("category","ID",catID));
        List<Post> posts = postRepository.findByCategory(category);
        return posts.stream().map(this::MapToResponse).toList();
    }

    public PostResponse getPostById(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->new UserDefinedException("Post","ID",id));
        return MapToResponse(post);
    }

    public void updatePost(PostRequest postRequest,Long id){
        Post post = postRepository.findById(id).orElseThrow(()->new UserDefinedException("Post","ID",id));
        post.setTitle(postRequest.getTitle());
        post.setImage(postRequest.getImage());
        post.setContent(postRequest.getContent());
        postRepository.save(post);
    }

    public void deletePost(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->new UserDefinedException("Post","ID",id));
        postRepository.delete(post);
    }


    private PostResponse MapToResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .image(post.getImage())
                .content(post.getContent())
                .user(MapToUserResponse(post.getUser()))
                .category(MapToCategoryResponse(post.getCategory()))
                .build();
    }

    private CategoryResponse MapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }

    private UserResponse MapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .about(user.getAbout())
                .build();
    }

}

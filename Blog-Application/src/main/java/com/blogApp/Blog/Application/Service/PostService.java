package com.blogApp.Blog.Application.Service;

import com.blogApp.Blog.Application.Dto.CategoryResponse;
import com.blogApp.Blog.Application.Dto.PostRequest;
import com.blogApp.Blog.Application.Dto.PostResponse;
import com.blogApp.Blog.Application.Dto.UserResponse;
import com.blogApp.Blog.Application.Exceptions.ResourceNotFoundException;
import com.blogApp.Blog.Application.Model.Category;
import com.blogApp.Blog.Application.Model.Post;
import com.blogApp.Blog.Application.Model.User;
import com.blogApp.Blog.Application.Repository.CategoryRepository;
import com.blogApp.Blog.Application.Repository.PostRepository;
import com.blogApp.Blog.Application.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public void createPost(PostRequest postRequest, Integer user_id, Integer category_id){
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User","Id",user_id));
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new ResourceNotFoundException("Category","Id",category_id));
        Post post = Post.builder()
                .title(postRequest.getTitle())
                .image("default.png")
                .content(postRequest.getContent())
                .addedDate(new Date())
                .user(user)
                .category(category)
                .build();
        postRepository.save(post);
    }

    public List<PostResponse> getAllPosts(Integer pageSize,Integer pageNumber){
        Pageable p = PageRequest.of(pageSize,pageNumber);
        Page<Post> pagePosts = postRepository.findAll(p);
        List<Post> allPosts = pagePosts.getContent();

        return postRepository.findAll().stream().map(this::MapToResponse).toList();
    }

    public PostResponse findPostById(int id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","Id",id));
        return MapToResponse(post);
    }

    public void deletePost(int id){
        postRepository.deleteById(id);
    }

    public List<PostResponse> findPostByUser(int userId){
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map(this::MapToResponse).toList();
    }

    public List<PostResponse> findPostByCategory(int categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Id",categoryId));
        List<Post> posts = postRepository.findByCategory(category);

        return posts.stream().map(this::MapToResponse).toList();
    }

    public PostResponse updatePost(PostRequest postRequest,Integer postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Id",postId));
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setImage(postRequest.getImageName());
        postRepository.save(post);
        return MapToResponse(post);
    }

    private PostResponse MapToResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .addedDate(post.getAddedDate())
                .image(post.getImage())
                .user(MapToUserResponse(post.getUser()))
                .category(MapToCategoryResponse(post.getCategory()))
                .build();
    }

    private CategoryResponse MapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .categoryTitle(category.getCategoryTitle())
                .categoryDescription(category.getCategoryDescription())
                .build();
    }

    private UserResponse MapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .about(user.getAbout())
                .password(user.getPassword())
                .build();
    }

}

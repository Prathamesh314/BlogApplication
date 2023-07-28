package com.blogApp.Blog.Application.service;

import com.blogApp.Blog.Application.dto.*;
import com.blogApp.Blog.Application.exception.UserDefinedException;
import com.blogApp.Blog.Application.model.Category;
import com.blogApp.Blog.Application.model.Comment;
import com.blogApp.Blog.Application.model.Post;
import com.blogApp.Blog.Application.model.User;
import com.blogApp.Blog.Application.repository.CategoryRepository;
import com.blogApp.Blog.Application.repository.CommentRepository;
import com.blogApp.Blog.Application.repository.PostRepository;
import com.blogApp.Blog.Application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    @Value("${project.image}")
    private String path;

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private final CommentRepository commentRepository;

    private final FileService fileService;

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

    public PaginationResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir){

        Sort sort = null;

        if (sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }
        else{
            sort = Sort.by(sortBy).descending();
        }

        Pageable p = PageRequest.of(pageNumber,pageSize, sort);;
        Page<Post> posts = postRepository.findAll(p);
        List<Post> allposts = posts.getContent();



        List<PostResponse> postResponses =  allposts.stream().map(this::MapToResponse).toList();

        PaginationResponse paginationResponse = PaginationResponse.builder()
                .posts(postResponses)
                .pageNumber(posts.getNumber())
                .pageSize(posts.getSize())
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .lastPage(posts.isLast())
                .build();

        return paginationResponse;

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

    public List<PostResponse> searchPosts(String keyword){
        return postRepository.findByTitleContaining(keyword).stream().map(this::MapToResponse).toList();
    }

    public void uploadImage(MultipartFile image, Long id) throws IOException {
        Post post = postRepository.findById(id).orElseThrow(()->new UserDefinedException("Post","ID",id));
        String name = fileService.uploadImage(path,image);
        post.setImage(name);
        postRepository.save(post);
    }


    private PostResponse MapToResponse(Post post) {
        List<Comment> comments = commentRepository.findByPostId(post.getId());
        List<CommentResponse> commentResponses = comments.stream().map(this::MapToCommentResponse).toList();
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .image(post.getImage())
                .content(post.getContent())
                .user(MapToUserResponse(post.getUser()))
                .category(MapToCategoryResponse(post.getCategory()))
                .comments(commentResponses)
                .build();
    }

    private CommentResponse MapToCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .comment(comment.getComment())
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

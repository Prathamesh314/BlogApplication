package com.blogApp.Blog.Application.controller;

import com.blogApp.Blog.Application.dto.FileResponse;
import com.blogApp.Blog.Application.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping
    public ResponseEntity<FileResponse> uploadImage(
            @RequestParam("image") MultipartFile image
    ) {
        String name = null;
        try {
            name = fileService.uploadImage(path,image);
        } catch (IOException e) {
            e.getStackTrace();
            return new ResponseEntity<>(new FileResponse(null,"Image is not uploaded"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileResponse(name,"Image uploaded successfully"),HttpStatus.CREATED);
    }

    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
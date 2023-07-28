package com.blogApp.Blog.Application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

    public String uploadImage(String path, MultipartFile file) throws IOException {

        // Extract file

        String name = file.getOriginalFilename();
        String ramdomID = UUID.randomUUID().toString();
        String fileName1 = ramdomID.concat(name.substring(name.lastIndexOf(".")));

        // create a full path

        String filePath = path + File.separator + fileName1;


        // create a folder if not created

        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName1;
    }

    public InputStream getResource(String path,String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }

}
package com.example.fashionblog.Service;

import com.example.fashionblog.DTO.PostDTO;
import com.example.fashionblog.Model.Post;

import java.util.List;

public interface PostService {
    String createPost(PostDTO post);
    List<Post> viewPost();
}

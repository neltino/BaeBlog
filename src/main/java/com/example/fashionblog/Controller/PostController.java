package com.example.fashionblog.Controller;

import com.example.fashionblog.DTO.PostDTO;
import com.example.fashionblog.Model.Post;
import com.example.fashionblog.Service.ServiceImpl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {
    @Autowired
    PostServiceImpl postService;
    @PostMapping("create-post")
    public String createPost(@RequestBody PostDTO post){

        return postService.createPost(post);
    }
    @GetMapping("/view-post")
    public List<Post>viewPost(){
        return postService.viewPost();
    }
}

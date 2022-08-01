package com.example.fashionblog.Controller;

import com.example.fashionblog.DTO.LikesDTO;
import com.example.fashionblog.Service.ServiceImpl.LikesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("likes")
public class LikesController {
    @Autowired
    LikesServiceImpl likesService;
    @PostMapping("/like")
    public int likePost(@RequestBody LikesDTO postId){
        return likesService.likePost(postId);
    }

    @PostMapping("/unlike")
    public int unlikePost(@RequestBody LikesDTO postId){
        return likesService.unlikePost(postId);
    }
    @GetMapping("/count-post")
    public int countByPostId(@RequestParam Long postId){
        return likesService.countLikesByPost(postId);
    }
}

package com.example.fashionblog.Controller;

import com.example.fashionblog.DTO.CommentDTO;
import com.example.fashionblog.Model.Comment;
import com.example.fashionblog.Service.ServiceImpl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    CommentServiceImpl commentService;
    @PostMapping("/post-comment")
    public String createComment(@RequestBody CommentDTO comment){
        return commentService.makeComment(comment);
    }

    @GetMapping("/view-all-comment")
    public List<Comment> viewAllComment(){
        return commentService.viewAllComment();
    }
}

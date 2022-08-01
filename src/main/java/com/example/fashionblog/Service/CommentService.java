package com.example.fashionblog.Service;

import com.example.fashionblog.DTO.CommentDTO;
import com.example.fashionblog.Model.Comment;

import java.util.List;

public interface CommentService {
    String makeComment(CommentDTO comment);
    List<Comment> viewAllComment();
}

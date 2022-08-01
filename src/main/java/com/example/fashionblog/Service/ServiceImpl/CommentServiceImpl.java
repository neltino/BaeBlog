package com.example.fashionblog.Service.ServiceImpl;

import com.example.fashionblog.CustomException.ForbiddenException;
import com.example.fashionblog.CustomException.NotAcceptableException;
import com.example.fashionblog.CustomException.NotFoundException;
import com.example.fashionblog.DTO.CommentDTO;
import com.example.fashionblog.Model.Comment;
import com.example.fashionblog.Repository.CommentRepository;
import com.example.fashionblog.Repository.PostRepository;
import com.example.fashionblog.Repository.ProductRepository;
import com.example.fashionblog.Repository.UsersRepository;
import com.example.fashionblog.Service.CommentService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Data
public class CommentServiceImpl implements CommentService {
    private final HttpSession httpSession;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UsersRepository usersRepository;
    @Override
    public String makeComment(CommentDTO comment) {
        if(httpSession.getAttribute("user_Id") == null){
            throw new NotAcceptableException("Please login in first!");
        }
        Comment com = new Comment();
        com.setDescription(comment.getDescription());
        com.setUserId((Long) httpSession.getAttribute("user_Id"));
        commentRepository.save(com);
        return "Comment has been posted successfully!";
    }

    @Override
    public List<Comment> viewAllComment() {
        if(httpSession.getAttribute("user_Id") == null){
            throw new NotAcceptableException("Please login first");
        }
        if(!httpSession.getAttribute("user_Role").toString().equals("ADMIN")){
            throw new ForbiddenException("Sorry, only ADMIN can view comments");
        }
        List list = commentRepository.findAll();
        if(list.size() == 0){
           throw new NotFoundException("No comment found!");
        }
        return list;
    }

}

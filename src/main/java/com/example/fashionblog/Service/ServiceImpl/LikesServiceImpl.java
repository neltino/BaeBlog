package com.example.fashionblog.Service.ServiceImpl;

import com.example.fashionblog.CustomException.NotAcceptableException;
import com.example.fashionblog.CustomException.NotFoundException;
import com.example.fashionblog.DTO.LikesDTO;
import com.example.fashionblog.Model.Likes;
import com.example.fashionblog.Repository.LikesRepository;
import com.example.fashionblog.Repository.PostRepository;
import com.example.fashionblog.Repository.UsersRepository;
import com.example.fashionblog.Service.LikesService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Data
public class LikesServiceImpl implements LikesService {
    private final HttpSession httpSession;
    @Autowired
    PostRepository postRepository;
    @Autowired
    LikesRepository likesRepository;
    @Autowired
    UsersRepository usersRepository;
    @Override
    public int likePost(LikesDTO postId) {
         if(httpSession.getAttribute("user_Id") == null){
            throw new NotAcceptableException("Please login first to like a post!");
        }
        if(likesRepository.findLikesByPostIdAndUserId((Long) httpSession.getAttribute("user_Id"), postId.getPostId()) != null){
            throw new NotAcceptableException("You have already liked this product!");
        }
        Likes likes = new Likes();
        likes.setPost(postRepository.findById(postId.getPostId()).orElseThrow(
                ()-> new NotFoundException("Not post with Id '" + postId.getPostId() + "' found!")));

        likes.setUsers(usersRepository.findById((Long) httpSession.getAttribute("user_Id")).orElseThrow(
                ()-> new NotFoundException("Sorry, please login or signup to like post!")
        ));
        likesRepository.save(likes);
        return likesRepository.countLikesByPostId(postId.getPostId());
    }

    @Override
    public int unlikePost(LikesDTO postId) {

        if(httpSession.getAttribute("user_Id") == null){
            throw new NotAcceptableException("Please login first to like a post!");
        }

        if(likesRepository.findLikesByPostIdAndUserId((Long) httpSession.getAttribute("user_Id"), postId.getPostId()) == null){
            throw new NotAcceptableException("You have Not liked this post!");
        }
       return likesRepository.deleteByPostIdAndUserId(postId.getPostId(), (Long) httpSession.getAttribute("user_Id"));


    }

    public int countLikesByPost(Long postId){
        return likesRepository.countLikesByPostId(postId);
    }
}

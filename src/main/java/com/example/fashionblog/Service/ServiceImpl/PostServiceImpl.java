package com.example.fashionblog.Service.ServiceImpl;

import com.example.fashionblog.CustomException.ForbiddenException;
import com.example.fashionblog.CustomException.NotAcceptableException;
import com.example.fashionblog.CustomException.NotFoundException;
import com.example.fashionblog.DTO.PostDTO;
import com.example.fashionblog.Model.Post;
import com.example.fashionblog.Repository.PostRepository;
import com.example.fashionblog.Repository.ProductRepository;
import com.example.fashionblog.Service.PostService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Data
public class PostServiceImpl implements PostService {
    private final HttpSession httpSession;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public String createPost(PostDTO post) {
        if(httpSession.getAttribute("user_Id") == null){
            throw new NotAcceptableException("Please login first!");
        }
        if(!httpSession.getAttribute("user_Role").toString().equals("ADMIN")){
            throw new ForbiddenException("Sorry, only ADMIN can create post");
        }
        Post pos = new Post();
        pos.setDescription(post.getDescription());
//        pos.setProduct(productRepository.findById(post.getProductId()).orElseThrow(
//                ()-> new NotFoundException("No product with id '" + post.getProductId() + "'")
//        ));
        postRepository.save(pos);
        return "Post has been created successfully!";
    }

    @Override
    public List<Post> viewPost() {
        List<Post> list = postRepository.findAll();
        if(list.size() == 0){
            throw new NotFoundException("No post available!");
        }
        return postRepository.findAll();
    }
}

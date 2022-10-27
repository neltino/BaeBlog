package com.example.fashionblog.Controller;

import com.example.fashionblog.DTO.PostDTO;
import com.example.fashionblog.Model.Post;
import com.example.fashionblog.Service.ServiceImpl.PostServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostServiceImpl postService;
    @PostMapping("/create-post")
    @Operation(
            tags = {"Post"},
            summary = "create post",
            description = "This endpoint enables admin to create post",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Post has been created successfully!", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "403", description = "Sorry, only admin can create post", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    public String createPost(@RequestBody PostDTO post){

        return postService.createPost(post);
    }
    @GetMapping("/view-post")
    @Operation(
            tags = {"Post"},
            summary = "view all posts",
            description = "This endpoint is for viewing all posts",
            responses = {
                    @ApiResponse(responseCode = "200", description = "list of all posts", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "No post found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    public List<Post>viewPost(){
        return postService.viewPost();
    }
}

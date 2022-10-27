package com.example.fashionblog.Controller;

import com.example.fashionblog.DTO.LikesDTO;
import com.example.fashionblog.Service.ServiceImpl.LikesServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikesController {
    @Autowired
    LikesServiceImpl likesService;
    @PostMapping("/like")
    @Operation(
            tags = {"Likes"},
            summary = "like post",
            description = "This endpoint enable logged-in users to like posts",
            responses = {
                    @ApiResponse(responseCode = "200", description = "post liked successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "406", description = "user error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    public int likePost(@RequestBody LikesDTO postId){
        return likesService.likePost(postId);
    }

    @PostMapping("/unlike")
    @Operation(
            tags = {"Likes"},
            summary = "unlike a post",
            description = "This endpoint enables a user to delete a previously liked post",
            responses = {
                    @ApiResponse(responseCode = "200", description = "post unliked successfully"),
                    @ApiResponse(responseCode = "406", description = "user error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    public int unlikePost(@RequestBody LikesDTO postId){
        return likesService.unlikePost(postId);
    }
    @GetMapping("/count-likes")
    @Operation(
            tags = {"Likes"},
            summary = "Number of likes",
            description = "Return total number of likes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "numerical value", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    public int countByPostId(@RequestParam Long postId){
        return likesService.countLikesByPost(postId);
    }
}

package com.example.fashionblog.Controller;

import com.example.fashionblog.DTO.CommentDTO;
import com.example.fashionblog.Model.Comment;
import com.example.fashionblog.Service.ServiceImpl.CommentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentServiceImpl commentService;
    @PostMapping("/post-comment")
    @Operation(
            tags = {"Comment"},
            summary = "Create user comment",
            description = "This endpoint facilitates the creation of comments on product",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Comment has been posted successfully!", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "406", description = "Please login first", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    public String createComment(@RequestBody CommentDTO comment){
        return commentService.makeComment(comment);
    }

    @GetMapping("/view-all-comment")
    @Operation(
            tags = {"Comment"},
            summary = "view all comments",
            description = "This endpoint enables the admin to view all user comments",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successful", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "500", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    public List<Comment> viewAllComment(){
        return commentService.viewAllComment();
    }
}

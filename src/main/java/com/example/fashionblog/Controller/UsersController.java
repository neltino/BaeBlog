package com.example.fashionblog.Controller;

import com.example.fashionblog.DTO.UsersDTO;
import com.example.fashionblog.Service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Data
@Scope("session")
@RequestMapping("/users")
public class UsersController {
    private final HttpSession httpSession;
    @Autowired
    UsersService userService;

    @PostMapping("/create-user")
    @Operation(
            tags = {"Users"},
            summary = "Create new user",
            description = "This endpoint is used to create a new user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "406", description = "Sorry, a user with this email already exits!", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))


            }
    )

    public String createUser(@RequestBody @Valid UsersDTO user){

        return userService.createUser(user);
    }
    @GetMapping("/login")
    @Operation(
            tags = {"Users"},
            summary = "user login",
            description = "This endpoint enables a user to login and per user-related functions",
            responses = {
                    @ApiResponse(responseCode = "200", description = "login successful", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "Incorrect username and/or password", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "406", description = "User not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    public String login(@RequestParam String username, @RequestParam String password){
        return userService.login(username, password);
    }
    @GetMapping("/logout")
    @Operation(
            tags = {"Users"},
            summary = "User logout",
            description = "This endpoint enables user logout",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Logout successful", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }

    )
    public String logout(){
        return userService.logout();
    }


}

package com.example.fashionblog.Controller;

import com.example.fashionblog.DTO.UsersDTO;
import com.example.fashionblog.Model.Users;
import com.example.fashionblog.Service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

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

    @GetMapping("/view-all")
    @Operation(
            tags = {"Users"},
            summary = "View All Users",
            description = "This endpoint enables the admin to view all users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "login successful", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "404", description = "Incorrect username and/or password", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "406", description = "User not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )

    public List<Users> login(@RequestParam String username, @RequestParam String password){
        return userService.viewAllUsers();
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

    @PostMapping("/verification{username}")
    @Operation(
            tags = {"Users"},
            summary = "User Email Verification",
            description = "This endpoint enables user to update account status after signup",
            responses = {
                    @ApiResponse(responseCode = "200", description = "email verification successful", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }

    )
    public String emailVerification(@RequestParam(name = "username") String username){
        return userService.updateUserStatus(username);
    }


}

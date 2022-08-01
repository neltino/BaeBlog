package com.example.fashionblog.Controller;

import com.example.fashionblog.DTO.UsersDTO;
import com.example.fashionblog.Service.ServiceImpl.UsersServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
    UsersServiceImpl userService;

    @PostMapping("/create-user")
    public String createUser(@RequestBody @Valid UsersDTO user){

        return userService.createUser(user);
    }
    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        return userService.login(username, password);
    }
    @GetMapping("/logout")
    public String logout(){
        return userService.logout();
    }


}

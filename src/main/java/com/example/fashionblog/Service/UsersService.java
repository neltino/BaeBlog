package com.example.fashionblog.Service;

import com.example.fashionblog.DTO.ProductDTO;
import com.example.fashionblog.DTO.UsersDTO;
import com.example.fashionblog.Model.Users;

import java.util.List;

public interface UsersService {
    String createUser(UsersDTO user);
    List<Users> viewAllUsers();
    String logout();
}

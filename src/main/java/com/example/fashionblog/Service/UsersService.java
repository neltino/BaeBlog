package com.example.fashionblog.Service;

import com.example.fashionblog.DTO.ProductDTO;
import com.example.fashionblog.DTO.UsersDTO;

public interface UsersService {
    String createUser(UsersDTO user);
    String login(String username, String password);
    String logout();
}

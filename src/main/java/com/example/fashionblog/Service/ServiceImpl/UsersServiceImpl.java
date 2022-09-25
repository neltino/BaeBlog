package com.example.fashionblog.Service.ServiceImpl;

import com.example.fashionblog.CustomException.NotAcceptableException;
import com.example.fashionblog.CustomException.NotFoundException;
import com.example.fashionblog.DTO.UsersDTO;
import com.example.fashionblog.Enum.Role;
import com.example.fashionblog.Model.Users;
import com.example.fashionblog.Repository.UsersRepository;
import com.example.fashionblog.Service.UsersService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Data
public class UsersServiceImpl implements UsersService {
   private final HttpSession httpSession;
    @Autowired
    UsersRepository usersRepository;



    @Override
    public String createUser(UsersDTO user) {

        if(usersRepository.findUsersByEmail(user.getEmail()) != null){
                throw new NotAcceptableException("Sorry, a user with this email already exits!");
            }

        Users users = Users.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(Role.USER)
                .password(user.getPassword())
                .isActive(false)
                .email(user.getEmail())
                .build();
        usersRepository.save(users);
        return  "Account created successfully!";
    }
    @Override
    public String login(String username, String password){
        if(httpSession.getAttribute("user_Id") != null){
            throw new NotAcceptableException("Current USER needs to logout first!");
        }
        if(usersRepository.findByEmailAndPassword(username, password) == null){
            throw new NotFoundException("Incorrect email or password");
        }
        Users user = usersRepository.findByEmailAndPassword(username, password);

        httpSession.setAttribute("user_Id", user.getId());
        httpSession.setAttribute("user_Role", user.getRole());
        return "Welcome " + user.getFirstName();
    }

    @Override
    public String logout() {
        if(httpSession.getAttribute("user_Id") == null){
            throw new NotFoundException("Sorry, you are not logged in!");
        }
        httpSession.invalidate();
        return "Logout successful!";
    }

}

package com.example.fashionblog.Service.ServiceImpl;

import com.example.fashionblog.CustomException.BadRequestException;
import com.example.fashionblog.CustomException.NotAcceptableException;
import com.example.fashionblog.CustomException.NotFoundException;
import com.example.fashionblog.DTO.UsersDTO;
import com.example.fashionblog.Enum.Role;
import com.example.fashionblog.Model.UserRole;
import com.example.fashionblog.Model.Users;
import com.example.fashionblog.Repository.UserRoleRepository;
import com.example.fashionblog.Repository.UsersRepository;
import com.example.fashionblog.Service.UsersService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Data
public class UsersServiceImpl implements UsersService, UserDetailsService {
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UsersRepository usersRepository;
    @Autowired
    private final UserRoleRepository userRoleRepository;
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findUsersByEmail(username);
        if(user == null){
            throw new NotFoundException("Incorrect email or password");
        }

        if(!user.isActive()){
            throw new BadRequestException("Please verify your email");
        }
        user.getRoles().forEach(
                role ->{
                    authorities.add(new SimpleGrantedAuthority(role.getRoles().name()));
                });
        return new User(user.getEmail(), user.getPassword(), authorities);
    }
    @Override
    public String createUser(UsersDTO user) {

        if(usersRepository.findUsersByEmail(user.getEmail()) != null){
                throw new NotAcceptableException("Sorry, a user with this email already exits!");
            }


        Users users = Users.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(passwordEncoder.encode(user.getPassword()))
                .isActive(false)
                .email(user.getEmail())
                .build();
        usersRepository.save(users);
        UserRole userRole = UserRole.builder()
                .roles(Role.USER)
                .build();
        userRoleRepository.save(userRole);
        return  "Account created successfully!";
    }

    @Override
    public List<Users> viewAllUsers() {

        return usersRepository.findAll();
    }


    @Override
    public String logout() {
        return "Logout successful!";
    }


}

package com.example.fashionblog.Service.ServiceImpl;

import com.example.fashionblog.Enum.Role;
import com.example.fashionblog.Model.UserRole;
import com.example.fashionblog.Model.Users;
import com.example.fashionblog.Repository.UserRoleRepository;
import com.example.fashionblog.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class AdminService implements CommandLineRunner {
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        if(userRoleRepository.findUserRoleByRoles(Role.ADMIN) == null){

            Users admin = Users.builder()
                    .firstName("Nelson")
                    .lastName("John")
                    .email("neljoez@gmail.com")
                    .isActive(true)
                    .password(encoder.encode("admin123"))
                    .build();
            usersRepository.save(admin);
            UserRole userRole = UserRole.builder()
                    .roles(Role.ADMIN)
                    .build();
            userRoleRepository.save(userRole);


        }
    }
}

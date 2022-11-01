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
import com.example.fashionblog.Service.MailSenderService;
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

    @Autowired
    private final MailSenderService mailSenderService;
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

        String subject = "Beablog Email Verification";
        String sender = "no-reply@beablog.com";
        String body = "<!DOCTYPE = html>" +
                "<html>" +
                "<head>" +
                "</head>"+
                "<body>" +
                "<h4 style = 'color:blue; text-align: center;' >Welcome to Beablog Fashion Membership</h4>"+
                "<p style = 'color: green;' >Please complete you registration by clicking the button below!</p>"+
                "<form action = 'http://localhost:8080/api/v1/users/verification' method = 'POST' enctype = 'multipart/form-data'>" +
                "<input type = 'hidden' value = '"+users.getEmail()+"' name = 'username' >"+
                "<button style = 'width: 50%; height: 50px; cursor: pointer; margin-left: 25%; color: white; background: blue; border-radius: 5px; border:none;' >Verify Email</button>" +
                "</form>"+
                "</body>"+
                "</html>";
        mailSenderService.sendMail(users.getEmail(), subject, body);
        return  "Account created successfully, please check your email to verify you account!";
    }

    @Override
    public List<Users> viewAllUsers() {

        return usersRepository.findAll();
    }


    @Override
    public String logout() {
        return "Logout successful!";
    }

    @Override
    public String updateUserStatus(String username) {
        System.out.println("Halo, testing, testing....");
        Users user = usersRepository.findUsersByEmail(username);
        if(user == null){
            throw new NotFoundException("User not found!");
        }
        user.setActive(true);
        usersRepository.save(user);

        return "Email verified successfully!";
    }


}

package com.example.fashionblog.DTO;

import com.example.fashionblog.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    @NotBlank(message = "Firstname cannot be blank!")
    @Size(min = 3, message = "Firstname must be 3 or more characters")
    private String firstName;
    @NotBlank(message = "Lastname cannot be blank!")
    @Size(min = 3, message = "Lastname must be 3 or more characters")
    private String lastName;
    private Role role;
    @Email(message = "Invalid email format provided, please check!")
    private String email;
    @Size(min = 5, message = "Password must be 5 or more characters")
    private String password;

}

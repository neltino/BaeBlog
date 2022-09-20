package com.example.fashionblog.Model;

import com.example.fashionblog.Enum.Role;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column( columnDefinition = "TEXT", nullable = false)
    private String firstName;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "TEXT")
    private Role role = Role.USER;
    @Column(columnDefinition = "TEXT", nullable = false, unique = true)
    @Email(message = "Email must be valid")
    private String email;
    @Column(columnDefinition = "TEXT", nullable = false)
    @Size(min = 5, message = "Password must be 5 or more characters")
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comment;
    @OneToMany
    private List<Likes> likes;

}

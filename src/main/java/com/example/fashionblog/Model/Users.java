package com.example.fashionblog.Model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column( columnDefinition = "TEXT", nullable = false)
    private String firstName;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String lastName;
    @Column(columnDefinition = "TEXT", nullable = false, unique = true)
    @Email(message = "Email must be valid")
    private String email;
    @Column
    private boolean isActive;
    @Column(columnDefinition = "TEXT", nullable = false)
    @Size(min = 5, message = "Password must be 5 or more characters")
    private String password;

    @OneToMany(mappedBy = "Id", fetch = FetchType.EAGER)
    private Collection<UserRole> roles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comment;
    @OneToMany
    private List<Likes> likes;

}

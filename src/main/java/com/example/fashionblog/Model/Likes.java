package com.example.fashionblog.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "post_Id", referencedColumnName = "id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users users;
}

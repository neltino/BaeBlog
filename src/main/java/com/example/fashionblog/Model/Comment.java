package com.example.fashionblog.Model;

import com.example.fashionblog.DTO.UsersResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long userId;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @CreationTimestamp
    private LocalDateTime commentTime;


}

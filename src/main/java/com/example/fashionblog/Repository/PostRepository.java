package com.example.fashionblog.Repository;

import com.example.fashionblog.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

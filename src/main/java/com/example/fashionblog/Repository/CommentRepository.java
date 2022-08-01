package com.example.fashionblog.Repository;

import com.example.fashionblog.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

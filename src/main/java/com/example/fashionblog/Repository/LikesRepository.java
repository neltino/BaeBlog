package com.example.fashionblog.Repository;

import com.example.fashionblog.Model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    @Query(value = "SELECT * FROM likes WHERE post_id = ? AND users_id = ?", nativeQuery = true)
    Likes findLikesByPostIdAndUserId(Long postId, Long userId);

    @Query(value = "SELECT COUNT(*) FROM likes WHERE post_id = ?", nativeQuery = true)
    int countLikesByPostId(Long postId);

    @Query(value = "DELETE FROM likes WHERE post_id = ? AND users_id = ?", nativeQuery = true)
    int deleteByPostIdAndUserId(Long postId, Long userId);

}

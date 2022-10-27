package com.example.fashionblog.Repository;

import com.example.fashionblog.Enum.Role;
import com.example.fashionblog.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findUsersByEmail(String email);
    @Query(value = "SELECT first_name FROM users WHERE Id = ?", nativeQuery = true)
    String findFirstById(Long Id);
}

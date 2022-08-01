package com.example.fashionblog.Repository;

import com.example.fashionblog.Enum.Role;
import com.example.fashionblog.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findUsersByRole(Role role);
    Users findUsersByEmail(String email);
    Users findByEmailAndPassword(String username, String password);
    @Query(value = "SELECT first_name FROM users WHERE Id = ?", nativeQuery = true)
    String findFirstById(Long Id);
}

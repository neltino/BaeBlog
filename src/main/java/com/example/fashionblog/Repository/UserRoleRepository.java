package com.example.fashionblog.Repository;

import com.example.fashionblog.Enum.Role;
import com.example.fashionblog.Model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Role findUserRoleByRoles(Role role);
}

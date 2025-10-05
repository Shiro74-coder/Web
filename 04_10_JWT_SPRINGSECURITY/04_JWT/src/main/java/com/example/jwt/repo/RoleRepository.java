package com.example.jwt.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jwt.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}

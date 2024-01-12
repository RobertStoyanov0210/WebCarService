package com.example.carservice.data.repository;

import com.example.carservice.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByAuthority(String authority);
}

package com.techgen.blog.repository;

import com.techgen.blog.entity.Role;
import com.techgen.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}

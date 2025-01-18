package com.koushan.niles.repository;

import com.koushan.niles.entity.Role;
import com.koushan.niles.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String s);
    List<User> findAllByRole(Role role);
}
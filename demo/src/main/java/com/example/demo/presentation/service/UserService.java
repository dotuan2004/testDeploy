package com.example.demo.presentation.service;

import com.example.demo.persistence.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    public Optional<User> findByUserName(String tenDangNhap);
}

package com.example.demo.persistence.Repository;


import com.example.demo.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RepositoryRestResource(path = "nguoi-dung")
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String tenDangNhap);

    boolean existsByEmail(String email);
    public Optional<User> findByUsername(@RequestParam("tenDangNhap") String tenDangNhap);



}

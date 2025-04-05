package com.example.demo.presentation.model.request.User;

import com.example.demo.persistence.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class PostUpdateUser {
    private Integer userId;
    private String lastName;
    private String firstName;
    private String password;
    private String email;
    List<Role> roleList;
    private Long phoneNumber;
}

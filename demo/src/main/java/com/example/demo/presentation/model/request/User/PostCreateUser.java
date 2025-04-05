package com.example.demo.presentation.model.request.User;
import lombok.Data;

@Data
public class PostCreateUser {
    private Integer userId;
    private String lastName;
    private String firstName;
    private String username;
    private String password;
    private String email;
    private Boolean active;
    private String activeCode;
}

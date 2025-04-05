package com.example.demo.DTO;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class UserDTO {

    private Integer userId;
    private String lastName;
    private String firstName;
    private String username;
    private String email;

}

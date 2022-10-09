package com.techgen.blog.model;

import lombok.Data;

@Data
public class RegisterUserDTO {
    private String name;
    private String username;
    private String email;
    private String password;

}

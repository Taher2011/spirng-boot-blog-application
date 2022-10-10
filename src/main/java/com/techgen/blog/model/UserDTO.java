package com.techgen.blog.model;

import lombok.Data;

@Data
public class UserDTO {
    private String usernameOrEmail;
    private String password;
}

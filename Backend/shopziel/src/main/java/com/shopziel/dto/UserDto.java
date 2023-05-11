package com.shopziel.dto;

import lombok.Data;

@Data
public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String profileImgUrl;

    private String email;

    private String password;
    
    private String role;
}

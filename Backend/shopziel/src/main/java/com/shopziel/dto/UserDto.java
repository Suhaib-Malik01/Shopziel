package com.shopziel.dto;

import com.shopziel.models.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String profileImgUrl;

    
    private String email;

    private String password;
    
    private Role role;
}

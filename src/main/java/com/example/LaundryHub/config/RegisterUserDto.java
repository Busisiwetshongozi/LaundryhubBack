package com.example.LaundryHub.config;

import com.example.LaundryHub.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RegisterUserDto {
    private String email;

    private String password;

    private String firstName;

    private Role role;

}

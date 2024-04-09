package com.example.Car_Rental.dto;

import com.example.Car_Rental.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private UserRole userrole;

}

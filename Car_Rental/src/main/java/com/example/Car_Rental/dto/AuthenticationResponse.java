package com.example.Car_Rental.dto;

import com.example.Car_Rental.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;
    private UserRole userrole;
    private Long userID;

}

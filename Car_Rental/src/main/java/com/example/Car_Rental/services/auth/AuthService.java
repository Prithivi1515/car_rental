package com.example.Car_Rental.services.auth;

import com.example.Car_Rental.dto.SignupRequest;
import com.example.Car_Rental.dto.UserDto;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);

    boolean hascustomerwithemail(String email);

}

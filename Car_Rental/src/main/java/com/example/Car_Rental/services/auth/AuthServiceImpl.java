package com.example.Car_Rental.services.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Car_Rental.dto.SignupRequest;
import com.example.Car_Rental.dto.UserDto;
import com.example.Car_Rental.entity.User;
import com.example.Car_Rental.enums.UserRole;
import com.example.Car_Rental.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public void createAdminAccount() {
        User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if (adminAccount == null) {
            User newAdminAccount = new User();
            newAdminAccount.setUsername("Admin");
            newAdminAccount.setEmail("admin@test.com");
            newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
            newAdminAccount.setUserRole(UserRole.ADMIN);
            userRepository.save(newAdminAccount);
            System.out.println("Admin account created");
        }
    }

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setPhoneNumber(signupRequest.getPhoneNumber());
        user.setUserRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        return userDto;
    }

    @Override
    public boolean hascustomerwithemail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

}

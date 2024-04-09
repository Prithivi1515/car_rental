package com.example.Car_Rental.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.Car_Rental.dto.AuthenticationRequest;
import com.example.Car_Rental.dto.AuthenticationResponse;
import com.example.Car_Rental.dto.SignupRequest;
import com.example.Car_Rental.dto.UserDto;
import com.example.Car_Rental.entity.User;
import com.example.Car_Rental.repository.UserRepository;
import com.example.Car_Rental.services.auth.AuthService;
import com.example.Car_Rental.services.jwt.UserService;
import com.example.Car_Rental.utils.JWTutil;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTutil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    private ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest) {
        if (authService.hascustomerwithemail(signupRequest.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
        UserDto createdCustomerDto = authService.createCustomer(signupRequest);
        if (createdCustomerDto == null) {
            return new ResponseEntity<>("Customer not created", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws BadCredentialsException, DisabledException, UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }
        final UserDetails userDetails = userService.userDetailsService()
                .loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> OptionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (OptionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserID(OptionalUser.get().getId());
            authenticationResponse.setUserrole(OptionalUser.get().getUserRole());
        }
        return authenticationResponse;
    }
}

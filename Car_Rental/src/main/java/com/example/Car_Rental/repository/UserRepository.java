package com.example.Car_Rental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Car_Rental.entity.User;
import com.example.Car_Rental.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    User findByUserRole(UserRole userrole);

}

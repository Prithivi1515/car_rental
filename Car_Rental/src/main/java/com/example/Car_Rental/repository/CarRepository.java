package com.example.Car_Rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Car_Rental.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}

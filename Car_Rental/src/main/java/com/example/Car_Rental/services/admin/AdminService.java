package com.example.Car_Rental.services.admin;

import java.io.IOException;
import java.util.List;

import com.example.Car_Rental.dto.CarDto;

public interface AdminService {

    boolean postCar(CarDto carDto) throws IOException;

    List<CarDto> getAllCars();

    void deleteCar(Long id);

}

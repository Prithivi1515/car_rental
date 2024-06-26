package com.example.Car_Rental.services.admin;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Car_Rental.dto.CarDto;
import com.example.Car_Rental.entity.Car;
import com.example.Car_Rental.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final CarRepository carRepository;

    @Override
    public boolean postCar(CarDto carDto) throws IOException {
        try {
            Car car = new Car();
            car.setName(carDto.getName());
            car.setBrand(carDto.getBrand());
            car.setColor(carDto.getColor());
            car.setPrice(carDto.getPrice());
            car.setType(carDto.getType());
            car.setYear(carDto.getYear());
            car.setTransmission(carDto.getTransmission());
            car.setDescription(carDto.getDescription());
            car.setImage(carDto.getImage().getBytes());
            carRepository.save(car);
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

}

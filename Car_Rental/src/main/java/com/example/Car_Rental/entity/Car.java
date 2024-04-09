package com.example.Car_Rental.entity;

import java.util.Date;

import com.example.Car_Rental.dto.CarDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String color;
    private String name;
    private String type;
    private String transmission;
    private long price;
    private Date year;
    private String Description;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    public CarDto getCarDto() {
        CarDto carDto = new CarDto();
        carDto.setId(id);
        carDto.setBrand(brand);
        carDto.setColor(color);
        carDto.setName(name);
        carDto.setType(type);
        carDto.setTransmission(transmission);
        carDto.setPrice(price);
        carDto.setYear(year);
        carDto.setDescription(Description);
        carDto.setReturnedImage(image);
        return carDto;
    }

}

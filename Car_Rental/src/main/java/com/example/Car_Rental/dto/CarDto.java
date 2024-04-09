package com.example.Car_Rental.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CarDto {
    private Long id;
    private String brand;
    private String color;
    private String name;
    private String type;
    private String transmission;
    private long price;
    private Date year;
    private MultipartFile image;
    private String Description;

    private byte[] returnedImage;

}

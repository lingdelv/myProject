package com.example.rental;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.rental.mapper")
public class AutoRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoRentalApplication.class, args);
    }

}

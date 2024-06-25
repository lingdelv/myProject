package com.example.rental;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.rental.mapper")
@EnableScheduling //开启定时任务
public class AutoRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoRentalApplication.class, args);
    }

}

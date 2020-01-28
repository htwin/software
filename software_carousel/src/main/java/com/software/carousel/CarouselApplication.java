package com.software.carousel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CarouselApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class,args);
    }
}

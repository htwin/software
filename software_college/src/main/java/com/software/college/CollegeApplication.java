package com.software.college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CollegeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollegeApplication.class,args);
    }

}

package com.software.user;

import com.software.common.util.IdWorker;
import com.software.common.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }

}

package com.software.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentApplication.class,args);
    }
}

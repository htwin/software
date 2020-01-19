package com.software.classify;

import com.software.common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class ClassifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassifyApplication.class,args);
    }

    //id生成器
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}

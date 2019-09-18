package com.guns21.example.sharding;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = {"com.guns21"})
public class ShardingApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ShardingApplication.class).run(args);
    }
}
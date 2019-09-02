package com.guns21.example.rocketmq;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = {"com.guns21"})
public class RocketMqApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RocketMqApplication.class).run(args);
    }
}
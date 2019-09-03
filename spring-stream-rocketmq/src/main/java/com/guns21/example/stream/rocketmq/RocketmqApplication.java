package com.guns21.example.stream.rocketmq;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = {"com.guns21"})
public class RocketmqApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RocketmqApplication.class).run(args);
    }
}
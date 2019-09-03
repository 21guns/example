package com.guns21.example.stream.rabbitmq;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = {"com.guns21"})
public class RabbitmqApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RabbitmqApplication.class).run(args);
    }
}
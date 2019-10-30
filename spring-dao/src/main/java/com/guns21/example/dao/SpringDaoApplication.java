package com.guns21.example.dao;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.guns21"})
public class SpringDaoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringDaoApplication.class).run(args);
    }
}

@Configuration
@ComponentScan
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
class Config{

}
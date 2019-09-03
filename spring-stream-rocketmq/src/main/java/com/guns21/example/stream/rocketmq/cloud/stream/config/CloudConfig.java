package com.guns21.example.stream.rocketmq.cloud.stream.config;

import com.guns21.example.stream.rocketmq.cloud.stream.TranslationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudConfig {


    @Bean
    public TranslationService translationService() {
        return new TranslationService();
    }


}

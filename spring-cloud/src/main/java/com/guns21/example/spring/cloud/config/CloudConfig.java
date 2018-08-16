package com.guns21.example.spring.cloud.config;

import com.guns21.example.spring.cloud.TranslationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudConfig {


    @Bean
    public TranslationService translationService() {
        return new TranslationService();
    }


}

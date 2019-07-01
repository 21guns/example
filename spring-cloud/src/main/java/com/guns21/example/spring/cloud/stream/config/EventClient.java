package com.guns21.example.spring.cloud.stream.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EventClient {
    String OUTPUT = "event-output";
    String INPUT = "event-input";

    @Output(OUTPUT)
    MessageChannel output();

    @Input(INPUT)
    SubscribableChannel input();

    @EnableBinding(EventClient.class)
    class EventConfig {
    }
}

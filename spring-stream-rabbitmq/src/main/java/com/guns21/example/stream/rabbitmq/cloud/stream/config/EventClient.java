package com.guns21.example.stream.rabbitmq.cloud.stream.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EventClient {
    String OUTPUT = "event-output";
    String A_OUTPUT = "a-event-output";
    String INPUT = "event-input";
    String A_INPUT = "a-event-input";
    String B_INPUT = "b-event-input";

//    String BINDING_A_A ="a-a-binding-input";

    @Output(OUTPUT)
    MessageChannel output();

    @Output(A_OUTPUT)
    MessageChannel aOutput();

    @Input(INPUT)
    SubscribableChannel input();

    @Input(A_INPUT)
    SubscribableChannel aInput();
    @Input(B_INPUT)
    SubscribableChannel bInput();

//    @Input(BINDING_A_A)
//    SubscribableChannel aaInput();

    @EnableBinding(EventClient.class)
    class EventConfig {
    }
}

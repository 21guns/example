package com.guns21.example.stream.rabbitmq.cloud.stream.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EventClient {
    String OUTPUT = "event-output";
    String A_OUTPUT = "a-event-output";
    String A_SELF_OUTPUT = "a-self-event-output";
    String INPUT = "event-input";
    String A_INPUT = "a-event-input";
    String B_INPUT = "b-event-input";

    @Output(OUTPUT)
    MessageChannel output();
    @Output(A_OUTPUT)
    MessageChannel aOutput();
    @Output(A_SELF_OUTPUT)
    MessageChannel aSelfOutput();

    @Input(INPUT)
    SubscribableChannel input();

    @Input(A_INPUT)
    SubscribableChannel aInput();
    @Input(B_INPUT)
    SubscribableChannel bInput();

    @EnableBinding(EventClient.class)
    class EventConfig {
    }
}

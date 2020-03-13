package com.guns21.example.stream.rabbitmq.cloud.stream.event;

import com.guns21.cloud.event.stream.EventWraper;
import com.guns21.event.domain.NotifyEvent;
import com.guns21.example.stream.rabbitmq.cloud.stream.EventDTO;
import org.springframework.messaging.Message;


public class UpdateEvent extends NotifyEvent<EventDTO> {
    public UpdateEvent() {
    }

    public UpdateEvent(EventDTO source) {
        super(source);
    }
}

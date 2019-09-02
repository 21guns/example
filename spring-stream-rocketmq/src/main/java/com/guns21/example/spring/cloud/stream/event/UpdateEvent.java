package com.guns21.example.spring.cloud.stream.event;

import com.guns21.cloud.event.domain.NotifyEvent;
import com.guns21.example.spring.cloud.stream.EventDTO;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;


public class UpdateEvent extends NotifyEvent<EventDTO> {
    public UpdateEvent() {
    }

    public UpdateEvent(EventDTO source) {
        super(source);
    }

    public static Message buildMessage(EventDTO source) {
        return  new UpdateEvent(source).toMessage();
    }

}

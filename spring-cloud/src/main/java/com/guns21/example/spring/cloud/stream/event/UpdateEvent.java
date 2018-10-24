package com.guns21.example.spring.cloud.stream.event;

import com.guns21.event.domain.NotifyEvent;
import com.guns21.example.spring.cloud.stream.EventDTO;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import static com.guns21.cloud.event.EventProcessor.EVENT_TYPE;

public class UpdateEvent extends NotifyEvent<Object> {
    public UpdateEvent() {
    }

    public UpdateEvent(Object source) {
        super(source);
    }

    public static Message buildMessage(EventDTO source) {
        return  new UpdateEvent(source).toMessage();
    }

    public Message toMessage() {
        return MessageBuilder.withPayload(this)
                .setHeader(EVENT_TYPE, this.getEventType())
                .build();
    }
}

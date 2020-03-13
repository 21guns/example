package com.guns21.example.stream.rocketmq.cloud.stream.event;

import com.guns21.event.domain.NotifyEvent;
import com.guns21.example.stream.rocketmq.cloud.stream.EventDTO;

public class AddEvent extends NotifyEvent<EventDTO> {
    public AddEvent() {
    }

    public AddEvent(EventDTO source) {
        super(source);
    }
}

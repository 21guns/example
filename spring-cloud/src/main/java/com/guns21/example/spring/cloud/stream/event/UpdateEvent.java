package com.guns21.example.spring.cloud.stream.event;

import com.guns21.event.domain.NotifyEvent;
import com.guns21.example.spring.cloud.stream.EventDTO;


public class UpdateEvent extends NotifyEvent<EventDTO> {
    public UpdateEvent() {
    }

    public UpdateEvent(EventDTO source) {
        super(source);
    }



}

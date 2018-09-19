package com.guns21.example.spring.cloud.stream.event;

import com.guns21.event.domain.NotifyEvent;

public class UpdateEvent extends NotifyEvent<Object> {
    public UpdateEvent() {
    }

    public UpdateEvent(Object source) {
        super(source);
    }
}

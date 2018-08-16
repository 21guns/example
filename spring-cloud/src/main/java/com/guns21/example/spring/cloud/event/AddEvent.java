package com.guns21.example.spring.cloud.event;

import com.guns21.event.domain.NotifyEvent;
import lombok.Data;

@Data
public class AddEvent extends NotifyEvent<Object> {
    public AddEvent() {
    }

    public AddEvent(Object source) {
        super(source);
    }
}

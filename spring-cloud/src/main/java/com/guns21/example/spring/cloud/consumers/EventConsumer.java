package com.guns21.example.spring.cloud.consumers;

import com.guns21.cloud.event.EventProcessor;
import com.guns21.cloud.event.annotation.EventBusListener;
import com.guns21.example.spring.cloud.event.AddEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(EventProcessor.class);
    @EventBusListener
    public void accept(AddEvent addEvent) {

        logger.debug("get event {}",addEvent);
    }

}

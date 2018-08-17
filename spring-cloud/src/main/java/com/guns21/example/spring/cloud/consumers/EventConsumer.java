package com.guns21.example.spring.cloud.consumers;

import com.guns21.cloud.event.annotation.EventBusListener;
import com.guns21.example.spring.cloud.event.AddEvent;
import com.guns21.example.spring.cloud.event.UpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    @EventBusListener(value = "headers['eventType']=='AddEvent'")
    public void accept(@Payload AddEvent addEvent) {

        logger.info("get event {}",addEvent);
    }
    @EventBusListener(value = "headers['eventType']=='UpdateEvent'")
    public void accept1(@Payload UpdateEvent addEvent) {

        logger.info("get1 event {}",addEvent);
//        throw new RuntimeException("dsdf");
    }
}

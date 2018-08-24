package com.guns21.example.spring.cloud.consumers;

import com.guns21.cloud.event.CloudEventConstants;
import com.guns21.cloud.event.EventBusClient;
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
    @EventBusListener(target = EventBusClient.INPUT, condition = CloudEventConstants.EVENT_HEADERS_EVENT_TYPE + "'AddEvent'")
    public void accept(@Payload AddEvent addEvent) {

        logger.info("get event {}",addEvent);
    }
    @EventBusListener(target = EventBusClient.INPUT, condition = CloudEventConstants.EVENT_HEADERS_EVENT_TYPE + "'UpdateEvent'")
    public void accept1(@Payload UpdateEvent addEvent) {

        logger.info("get1 event {}",addEvent);
//        throw new RuntimeException("dsdf");
    }
}

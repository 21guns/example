package com.guns21.example.spring.cloud.stream.consumers;

import com.guns21.cloud.event.EventConstant;
import com.guns21.example.spring.cloud.stream.config.EventClient;
import com.guns21.example.spring.cloud.stream.event.AddEvent;
import com.guns21.example.spring.cloud.stream.event.UpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

//@EnableBinding(EventClient.class)
@Component
public class EventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);

//    @StreamListener(target = EventBusClient.INPUT, condition = CloudEventConstants.EVENT_HEADERS_EVENT_TYPE + "'AddEvent'")
    public void accept(@Payload AddEvent addEvent) {

        logger.info("get event {}",addEvent);
    }
    @StreamListener(target = EventClient.INPUT, condition = EventConstant.EVENT_HEADERS_EVENT_TYPE + "'UpdateEvent'")
    public void accept1(@Payload UpdateEvent addEvent) {

        logger.info("get1 event {}",addEvent);
//        throw new RuntimeException("dsdf");
    }
}

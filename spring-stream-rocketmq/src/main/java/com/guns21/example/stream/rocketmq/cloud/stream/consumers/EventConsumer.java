package com.guns21.example.stream.rocketmq.cloud.stream.consumers;

import com.guns21.cloud.event.EventConstant;
import com.guns21.example.stream.rocketmq.cloud.stream.config.EventClient;
import com.guns21.example.stream.rocketmq.cloud.stream.event.AddEvent;
import com.guns21.example.stream.rocketmq.cloud.stream.event.UpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@EnableBinding(EventClient.class)
@Component
public class EventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    @StreamListener(target = EventClient.INPUT, condition = EventConstant.EVENT_HEADERS_EVENT_TYPE + "'AddEvent'")
    public void accept(@Payload AddEvent addEvent) {

        logger.warn("AddEvent {}",addEvent);
    }
    @StreamListener(target = EventClient.INPUT, condition = EventConstant.EVENT_HEADERS_EVENT_TYPE + "'UpdateEvent'")
    public void accept1(@Payload UpdateEvent addEvent) throws IOException {

//        try {
//            Thread.sleep(50000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        logger.info("UpdateEvent {}",addEvent);

        switch (addEvent.getSource().getName()) {
            /*
                 手动ack是出现异常需要手动处理改消息

             */
            case "1": {
                throw new RuntimeException("dsdf");
                }
            case "2": {
                /*
                    回退消息
                    requeue = true 回退到源队列
                */
//                channel.basicNack(deliveryTag, false, false);
                break;
            }
            case "3": {
                /*
                    手动ack
                    1. acknowledge-mode: MANUAL
                    2.channel.basicAck(deliveryTag, false);
                */
//                channel.basicAck(deliveryTag, false);
                break;
            }
            default: {
                System.err.println("Thead[" + Thread.currentThread().getName() +"] unknow type :: "+addEvent.getSource().getName());
//                channel.basicAck(deliveryTag, false);
                break;
            }
        }
    }


}

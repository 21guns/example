package com.guns21.example.stream.rabbitmq.cloud.stream.consumers;

import com.guns21.cloud.event.EventConstant;
import com.guns21.example.stream.rabbitmq.cloud.stream.event.AddEvent;
import com.guns21.example.stream.rabbitmq.cloud.stream.event.UpdateEvent;
import com.guns21.example.stream.rabbitmq.cloud.stream.config.EventClient;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

//@EnableBinding(EventClient.class)
@Component
public class EventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);
//    @StreamListener(target = EventBusClient.INPUT, condition = CloudEventConstants.EVENT_HEADERS_EVENT_TYPE + "'AddEvent'")
    public void accept(@Payload AddEvent addEvent) {

        logger.info("get event {}",addEvent);
    }
//    @StreamListener(target = EventClient.INPUT, condition = EventConstant.EVENT_HEADERS_EVENT_TYPE + "'UpdateEvent'")
    public void accept1(@Payload UpdateEvent addEvent,
                        @Header(AmqpHeaders.CHANNEL) Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {

//        try {
//            Thread.sleep(50000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        logger.info("+++++++++++++++ event {}",addEvent);

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
                channel.basicNack(deliveryTag, false, false);
                break;
            }
            case "3": {
                /*
                    手动ack
                    1. acknowledge-mode: MANUAL
                    2.channel.basicAck(deliveryTag, false);
                */
                channel.basicAck(deliveryTag, false);
                break;
            }
            default: {
                System.err.println("Thead[" + Thread.currentThread().getName() +"] unknow type :: "+addEvent.getSource().getName());
                channel.basicAck(deliveryTag, false);
                break;
            }
        }
    }

//    @StreamListener(target = EventClient.A_INPUT, condition = EventConstant.EVENT_HEADERS_EVENT_TYPE + "'UpdateEvent'")
    public void acceptA(UpdateEvent addEvent) {

        logger.info(" Thread [{}] ----AAAA------- event {}",Thread.currentThread().getName(), addEvent);
//        throw new IllegalStateException();
    }

    @StreamListener(target = EventClient.B_INPUT,
            condition = EventConstant.EVENT_HEADERS_EVENT_TYPE + "'UpdateEvent' and headers['type']== 'b' ")
    public void acceptB(UpdateEvent addEvent) {
        logger.info(" Thread [{}] ----BBBB------- event {}",Thread.currentThread().getName(), addEvent);
    }
//    @StreamListener(target = EventClient.B_INPUT, condition = EventConstant.EVENT_HEADERS_EVENT_TYPE + "'*'")
    public void acceptBALL(UpdateEvent addEvent) {
        logger.info("-----*********----- event {}",addEvent);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "b-input", durable = "true"),
            exchange = @Exchange(name = "service-output",
                    type = ExchangeTypes.TOPIC,
                    ignoreDeclarationExceptions = "false"),
            key = {"#"})
    )
    public void acceptRabbitEvent(Message message, Channel channel) {
        logger.info("headers{} ", message.getMessageProperties().getHeaders());
        logger.info("-----*********----- event {}",new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
